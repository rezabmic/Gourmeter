package cz.cvut.fel.jee.gourmeter.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import cz.cvut.fel.jee.gourmeter.bo.Category;
import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.bo.Recommendation;
import cz.cvut.fel.jee.gourmeter.bo.Tag;
import cz.cvut.fel.jee.gourmeter.bo.User;
import cz.cvut.fel.jee.gourmeter.bo.UserRole;

@Stateless
public class DataSessionBean implements DataSessionLocal {

	@PersistenceContext(unitName = "GourmeterPU")
	private EntityManager em;

	@Override
	public List<CateringFacility> findFacilitiesByGPS(
			CoordinateSearchWrapper csw) {

		TypedQuery<CateringFacility> q = getFacilityGPSQuery(csw,
				"CateringFacility.findByCoordinates");

		return q.getResultList();
	}

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

	@Override
	public UserRole findRoleByName(String roleName) {
		TypedQuery<UserRole> q = em.createNamedQuery("UserRole.findByName",
				UserRole.class);
		q.setParameter("name", roleName);

		List<UserRole> resultList = q.getResultList();
		if (!resultList.isEmpty()) {
			return resultList.get(0);
		}
		return null;
	}

	@Override
	public User findUserByLogin(String login) {
		TypedQuery<User> q = em
				.createNamedQuery("User.findByLogin", User.class);
		q.setParameter("login", login);

		List<User> resultList = q.getResultList();
		if (!resultList.isEmpty()) {
			return resultList.get(0);
		}
		return null;
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
		TypedQuery<Category> q = em.createNamedQuery("Category.findById",
				Category.class);
		q.setParameter("id", id);
		Category c = q.getSingleResult();
		if (c != null)
			return c.getTags();
		else
			return new ArrayList<>();
	}

	@Override
	public List<Category> getAllCategories() {
		TypedQuery<Category> q = em.createNamedQuery("Category.getAll",
				Category.class);
		return q.getResultList();
	}

	@Override
	public void addRecommendation(Long tagId, Long facilityId, Long userId,
			Boolean recommended) {
		// TODO - budeme kontrolovat pritomnost Recommendation v DB??

		CateringFacility cf = this.em.find(CateringFacility.class, facilityId);
		Tag t = this.em.find(Tag.class, tagId);
		User u = this.em.find(User.class, userId);

		if ((cf != null) && (t != null) && (u != null)) {
			Recommendation r = new Recommendation();
			r.setTag(t);
			r.setUser(u);
			r.setCateringFacility(cf);
			r.setRecommended(recommended);
			this.em.persist(r);
		}

	}
}
