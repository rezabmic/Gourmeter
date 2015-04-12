package cz.cvut.fel.jee.gourmeter.ejb;

import java.util.List;

import javax.ejb.Local;

import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.dto.CateringFacilityCreateDTO;
import cz.cvut.fel.jee.gourmeter.dto.MapPositionDTO;
import cz.cvut.fel.jee.gourmeter.dto.MarkerDTO;

@Local
public interface FacilitySessionLocal {

	public List<MarkerDTO> getFacilitiesInArea(MapPositionDTO position);

	public List<MarkerDTO> findFacilitiesInAreaByCategory(
			long categoryID, MapPositionDTO position);
	
	public List<MarkerDTO> findFacilitiesInAreaByCategories(
			List<Long> categories, MapPositionDTO position);

	public void createOrUpdateFacility(CateringFacilityCreateDTO dto, Long userId);

	public CateringFacility getFacilityById(Long id);

}
