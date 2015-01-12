package cz.cvut.fel.jee.gourmeter.ejb;

import java.util.List;

import javax.ejb.Local;

import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.bo.Tag;

@Local
public interface DataSessionLocal {

	public List<CateringFacility> findFacilitiesByGPS(CoordinateSearchWrapper csw);

	public List<CateringFacility> findFacilitiesByGPSAndTag(CoordinateSearchWrapper csw, Tag tag);

}
