package cz.cvut.fel.jee.gourmeter.ejb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.bo.Tag;

public class DataSessionBean implements DataSessionLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<CateringFacility>
			findFacilitiesByGPS(CoordinateSearchWrapper csw) {

		TypedQuery<CateringFacility> q = getFacilityGPSQuery(csw,
				"CateringFacility.findByCoordinates");

		return q.getResultList();
	}

	@Override
	public List<CateringFacility>
			findFacilitiesByGPSAndTag(CoordinateSearchWrapper csw, Tag tag) {

		TypedQuery<CateringFacility> q = getFacilityGPSQuery(csw,
				"CateringFacility.findByCoordinatesAndTag");
		q.setParameter("tag", tag);

		return q.getResultList();
	}

	private TypedQuery<CateringFacility>
			getFacilityGPSQuery(CoordinateSearchWrapper csw, String queryName) {

		TypedQuery<CateringFacility> q = em.createNamedQuery(queryName, CateringFacility.class);
		q.setParameter("latitudeMax", csw.getLatitudeMax());
		q.setParameter("latitudeMin", csw.getLatitudeMin());
		q.setParameter("longitudeMax", csw.getLongitudeMax());
		q.setParameter("longitudeMin", csw.getLongitudeMin());
		return q;
	}
}
