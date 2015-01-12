package cz.cvut.fel.jee.gourmeter.ejb;

import java.util.List;

import javax.ejb.Local;

import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;

@Local
public interface FacilitySessionLocal {

	public List<CateringFacility> getFacilitiesInArea(double latitude,
														double longitude);

	public List<CateringFacility> getFacilitiesInArea(double latitude,
														double longitude,
														double kilometerSearchDistance);

	public List<CateringFacility> getFacilitiesInArea(double latitude,
														double longitude,
														double kilometerSearchDistance,
														long tagId);

}
