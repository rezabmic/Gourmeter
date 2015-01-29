package cz.cvut.fel.jee.gourmeter.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import cz.cvut.fel.jee.gourmeter.bo.Category;
import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
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
		return new ArrayList<>();
	}

	@Override
	public List<Tag> getTagsForCategory(Long id) {
		return new ArrayList<>();
	}

	@Override
	public List<Category> getAllCategories() {
		return new ArrayList<>();
	}
}
