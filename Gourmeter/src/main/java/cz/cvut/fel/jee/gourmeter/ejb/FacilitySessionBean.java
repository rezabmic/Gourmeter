package cz.cvut.fel.jee.gourmeter.ejb;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.cvut.fel.jee.gourmeter.bo.Category;
import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.bo.OpeningHours;
import cz.cvut.fel.jee.gourmeter.bo.Tag;
import cz.cvut.fel.jee.gourmeter.bo.User;
import cz.cvut.fel.jee.gourmeter.dto.CateringFacilityCreateDTO;
import cz.cvut.fel.jee.gourmeter.dto.CateringFacilityDTO;
import cz.cvut.fel.jee.gourmeter.dto.MapPositionDTO;
import cz.cvut.fel.jee.gourmeter.dto.MarkerDTO;
import cz.cvut.fel.jee.gourmeter.dto.OpeningHoursDTO;
import cz.cvut.fel.jee.gourmeter.dto.OpeningHoursDTO.Day;
import cz.cvut.fel.jee.gourmeter.dto.TagDTO;

@Stateless
public class FacilitySessionBean implements FacilitySessionLocal {

	private static final Logger log = LoggerFactory
			.getLogger(FacilitySessionBean.class);

	/**
	 * Conversion ratio for kilometer to coordinate degree. TODO check this
	 * value
	 */
	private static final double COORDINATE_TO_KM = 0.009132;

	/**
	 * Default search radius is 3 kilometers from actual position.
	 */
	private static final double DEFAULT_SEARCH_RADIUS_KM = 3;

	@PersistenceContext(unitName = "GourmeterPU")
	private EntityManager em;

	@EJB
	private DataSessionLocal dao;

	@Override
	public CateringFacility getFacilityById(Long id) {

		Query query = em.createQuery("select c from CateringFacility c left join fetch c.recommendations left join fetch c.category where c.id=:cfID").setParameter("cfID", id);
		
		
//		TypedQuery<CateringFacility> q = em.createNamedQuery(
//				"CateringFacility.findById", CateringFacility.class);
//		q.setParameter("id", id);
//		List<CateringFacility> resultList = q.getResultList();
		List<CateringFacility> resultList = (List<CateringFacility>) query.getResultList();
		
		query = em.createQuery("select c from CateringFacility c left join fetch c.openingHours where c.id=:cfID").setParameter("cfID", id);
		resultList = (List<CateringFacility>) query.getResultList();
		
		query = em.createQuery("select c from CateringFacility c left join fetch c.tags where c.id=:cfID").setParameter("cfID", id);
		resultList = (List<CateringFacility>) query.getResultList();
		
		if (!resultList.isEmpty()) {
			return resultList.get(0);
		}
		return null;
	}
	
	@Override
	public List<CateringFacility> getFacilitiesInArea(	double latitude,
														double longitude) {
		// use default circle radius
		CoordinateSearchWrapper csw = getCoordinatesWrapper(latitude,
				longitude, DEFAULT_SEARCH_RADIUS_KM);
		return dao.findFacilitiesByGPS(csw);
	}

	@Override
	public List<CateringFacility> getFacilitiesInArea(	double latitude,
														double longitude,
														double kmCircle) {

		CoordinateSearchWrapper csw = getCoordinatesWrapper(latitude,
				longitude, kmCircle);
		return dao.findFacilitiesByGPS(csw);
	}

	@Override
	public List<MarkerDTO> getFacilitiesInArea(	double latitude,
												double longitude,
												double kmCircle,
												long tagId) {

		Tag tag = em.find(Tag.class, tagId);
		if (tag == null) {
			String msg = "No tag found for id : " + tagId;
			log.warn("No tag found for id : " + tagId);
			throw new IllegalArgumentException(msg);
		}

		CoordinateSearchWrapper csw = getCoordinatesWrapper(latitude,
				longitude, kmCircle);
		List<CateringFacility> facilities = dao.findFacilitiesByGPSAndTag(csw,
				tag);
		return convertMarkerDTOs(facilities);
	}

	@Override
	public List<MarkerDTO> getFacilitiesInArea(MapPositionDTO p) {
		CoordinateSearchWrapper csw = new CoordinateSearchWrapper(
				p.getLongitudeBottom(), p.getLongitudeTop(),
				p.getLatitudeBottom(), p.getLatitudeTop());
		return convertMarkerDTOs(dao.findFacilitiesByGPS(csw));
	}

	@Override
	public void createOrUpdateFacility(CateringFacilityCreateDTO dto, Long userId) {
		if (dto == null || dto.getAddress() == null) {
			throw new IllegalArgumentException("Wrong dto format.");
		}
		DateFormat df = new SimpleDateFormat("HH:mm");

		CateringFacility f = new CateringFacility();
		f.setId(dto.getId());
		f.setCity(dto.getAddress().getCity());
		f.setCityDistrict(null); // TODO
		f.setDescription(dto.getDescription());
		f.setHouseNumber(dto.getAddress().getHouseNumber());
		f.setLatitude(dto.getLatitude());
		f.setLongitude(dto.getLongitude());
		try {
			f.setMenuFrom(df.parse(dto.getMenu().getFrom()));
			f.setMenuTo(df.parse(dto.getMenu().getTo()));
		} catch (ParseException | NullPointerException e) {
			log.warn(e.getMessage());
		}
		f.setMenuUrl(dto.getMenu().getUrl());
		f.setName(dto.getTitle());
		f.setStreet(dto.getAddress().getStreet());
		f.setUrl(dto.getUrl());

		Category category = em.find(Category.class, dto.getCategoryId());
		f.setCategory(category);

		User creator = em.find(User.class, userId);
		if (creator == null) {
			log.warn("Catering facility creator not found");
		}
		f.setCreator(creator);
		setOpeningHours(dto.getOpeningHours(), df, f);
		setTags(dto.getTags(), f);

		if (f.getId() == null)
			em.persist(f);
		else
			em.merge(f);
	}

	private void setTags(List<TagDTO> tags, CateringFacility f) {
		if (tags == null || tags.isEmpty()) {
			log.warn("Empty tags.");
			return;
		}

		for (TagDTO t : tags) {
			TypedQuery<Tag> q = em
					.createNamedQuery("Tag.findByName", Tag.class);
			q.setParameter("name", t.getName());
			Tag tag = null;
			try {
				tag = q.getSingleResult();
			} catch (NoResultException e) {
				// TODO
				tag = new Tag();
				tag.setName(t.getName());
				tag.setCategory(null); // TODO
				em.persist(tag);
				// throw new IllegalArgumentException("unknown tag '" +
				// t.getName() + "'");
			}

			f.getTags().add(tag);
		}
	}

	private void setOpeningHours(	List<OpeningHoursDTO> openingHours,
									DateFormat df,
									CateringFacility f) {
		if (openingHours == null || openingHours.isEmpty()) {
			log.warn("Empty opening hours.");
			return;
		}

		for (OpeningHoursDTO ohDto : openingHours) {
			for (Day day : ohDto.getDays()) {
				// for all open days
				if (day.getSelected()) {
					OpeningHours oh = new OpeningHours();
					// required
					try {
						oh.setTimeFrom(df.parse(ohDto.getOpenFrom()));
						oh.setTimeTo(df.parse(ohDto.getOpenTo()));
					} catch (ParseException | NullPointerException e) {
						log.warn(e.getMessage());
						continue;
					}

					try {
						oh.setBreakFrom(df.parse(ohDto.getBreakFrom()));
						oh.setBreakTo(df.parse(ohDto.getBreakTo()));
					} catch (ParseException | NullPointerException e) {
						log.warn("createNewFacility: opening breaks : "
								+ e.getMessage());
					}

					oh.setDayNum(day.getDayNum());
					oh.setFacility(f);
					f.getOpeningHours().add(oh);
				}
			}
		}
	}

	private CoordinateSearchWrapper getCoordinatesWrapper(	double latitude,
															double longitude,
															double kmCircle) {
		double radius = getSearchRadius(kmCircle);
		double latMin = latitude - radius;
		double latMax = latitude + radius;
		double longMin = longitude - radius;
		double longMax = longitude + radius;
		CoordinateSearchWrapper csw = new CoordinateSearchWrapper(longMin,
				longMax, latMin, latMax);

		return csw;
	}

	private double getSearchRadius(double kilometerDistance) {
		// return search radius
		return COORDINATE_TO_KM * kilometerDistance / 2;
	}

	

	private List<MarkerDTO> convertMarkerDTOs(List<CateringFacility> facilities) {
		List<MarkerDTO> result = new ArrayList<>();
		for (CateringFacility facility : facilities) {
			result.add(new MarkerDTO(facility));
		}
		return result;
	}

}
