package cz.cvut.fel.jee.gourmeter.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import cz.cvut.fel.jee.gourmeter.bo.Category;
import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.bo.Recommendation;
import cz.cvut.fel.jee.gourmeter.bo.Tag;
import cz.cvut.fel.jee.gourmeter.bo.User;

@Stateless
public class DataSessionBean implements DataSessionLocal {

	@PersistenceContext(unitName = "GourmeterPU")
	private EntityManager em;

	@Override
	public List<CateringFacility> findFacilitiesByGPSAndTag(
			CoordinateSearchWrapper csw, Tag tag) {

		TypedQuery<CateringFacility> q = getFacilityGPSQuery(csw,
				"CateringFacility.findByCoordinatesAndTag");
		q.setParameter("tag", tag);

		return q.getResultList();
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return em.getCriteriaBuilder();
	}

	@Override
	public <T> List<T> executeCriteriaQuery(CriteriaQuery<T> cq) {
		return em.createQuery(cq).getResultList();
	}

	private TypedQuery<CateringFacility> getFacilityGPSQuery(
			CoordinateSearchWrapper csw, String queryName) {

		TypedQuery<CateringFacility> q = em.createNamedQuery(queryName,
				CateringFacility.class);
		q.setParameter("latitudeMax", csw.getLatitudeMax());
		q.setParameter("latitudeMin", csw.getLatitudeMin());
		q.setParameter("longitudeMax", csw.getLongitudeMax());
		q.setParameter("longitudeMin", csw.getLongitudeMin());
		return q;
	}

	@Override
	public List<Tag> getAllTags() {
		TypedQuery<Tag> q = em.createNamedQuery("Tag.getAll", Tag.class);
		return q.getResultList();
	}

	@Override
	public List<Tag> getTagsForCategory(Long id) {
		// TODO
		Query query = em.createQuery("select t from Tag t left join fetch t.category as c where c.id = :id")
				.setParameter("id", id);
		
		return query.getResultList();
	}
	
	@Override
	public List<Tag> getTagsForCategories(List<Long> categories) {
		// TODO
		Query query = em.createQuery("select t from Tag t left join fetch t.category as c where c.id IN :list")
				.setParameter("list", categories);
		
		return query.getResultList();
	}

	@Override
	public List<Category> getAllCategories() {
		TypedQuery<Category> q = em.createNamedQuery("Category.getAll",
				Category.class);
		return q.getResultList();
	}

	@Override
	public void addRecommendation(Long tagId, Long facilityId, Long userId,
			Boolean recommended) throws IllegalArgumentException{
		// TODO - budeme kontrolovat pritomnost Recommendation v DB??

		CateringFacility cf = em.find(CateringFacility.class, facilityId);
		Tag t = em.find(Tag.class, tagId);
		User u = em.find(User.class, userId);

		if ((cf != null) && (t != null) && (u != null)) {
			Recommendation r = new Recommendation();
			r.setTag(t);
			r.setUser(u);
			r.setCateringFacility(cf);
			r.setRecommended(recommended);
			em.persist(r);
		} else{
			throw new IllegalArgumentException();
		}

	}

	
}
