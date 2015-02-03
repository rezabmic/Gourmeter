package cz.cvut.fel.jee.gourmeter.ejb;

import java.util.List;

import javax.ejb.Local;

import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.dto.CateringFacilityDTO;
import cz.cvut.fel.jee.gourmeter.dto.MapPositionDTO;
import cz.cvut.fel.jee.gourmeter.dto.MarkerDTO;

@Local
public interface FacilitySessionLocal {

	public List<CateringFacility> getFacilitiesInArea(double latitude,
			double longitude);

	public List<MarkerDTO> getFacilitiesInArea(MapPositionDTO position);

	public List<CateringFacility> getFacilitiesInArea(double latitude,
			double longitude, double kilometerSearchDistance);

	public List<MarkerDTO> getFacilitiesInArea(double latitude,
			double longitude, double kilometerSearchDistance, long tagId);

	public void createOrUpdateFacility(CateringFacilityDTO dto, Long userId);

	public CateringFacility getFacilityById(Long id);

}
