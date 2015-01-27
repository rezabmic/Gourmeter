package cz.cvut.fel.jee.gourmeter.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.bo.Tag;

@Local
public interface DataSessionLocal {
	
	public CriteriaBuilder getCriteriaBuilder();
	
	public <T> List<T> executeCriteriaQuery(CriteriaQuery<T> cq);

	public List<CateringFacility> findFacilitiesByGPS(CoordinateSearchWrapper csw);

	public List<CateringFacility> findFacilitiesByGPSAndTag(CoordinateSearchWrapper csw, Tag tag);

}