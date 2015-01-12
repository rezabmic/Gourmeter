package cz.cvut.fel.jee.gourmeter.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.bo.Tag;

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

	@PersistenceContext
	private EntityManager em;

	@EJB
	private DataSessionLocal dao;

	@Override
	public List<CateringFacility> getFacilitiesInArea(double latitude,
														double longitude) {
		// use default circle radius
		CoordinateSearchWrapper csw = getCoordinatesWrapper(latitude,
				longitude, DEFAULT_SEARCH_RADIUS_KM);
		return dao.findFacilitiesByGPS(csw);
	}

	@Override
	public List<CateringFacility> getFacilitiesInArea(double latitude,
														double longitude,
														double kmCircle) {

		CoordinateSearchWrapper csw = getCoordinatesWrapper(latitude,
				longitude, kmCircle);
		return dao.findFacilitiesByGPS(csw);
	}

	@Override
	public List<CateringFacility> getFacilitiesInArea(double latitude,
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
		return dao.findFacilitiesByGPSAndTag(csw, tag);
	}

	private CoordinateSearchWrapper getCoordinatesWrapper(double latitude,
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

}
