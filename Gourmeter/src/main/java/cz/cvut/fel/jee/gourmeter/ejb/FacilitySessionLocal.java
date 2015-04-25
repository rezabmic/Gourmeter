package cz.cvut.fel.jee.gourmeter.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.dto.CateringFacilityCreateDTO;
import cz.cvut.fel.jee.gourmeter.dto.MapPositionDTO;
import cz.cvut.fel.jee.gourmeter.dto.MarkerDTO;
import cz.cvut.fel.jee.gourmeter.dto.ReviewDTO;

@Local
public interface FacilitySessionLocal {

	public List<MarkerDTO> getFacilitiesInArea(MapPositionDTO position);

	public List<MarkerDTO> findFacilitiesInAreaByCategory(
			long categoryID, MapPositionDTO position);
	
	public List<MarkerDTO> findFacilitiesInAreaByCategories(
			List<Long> categories, MapPositionDTO position);

	public void createOrUpdateFacility(CateringFacilityCreateDTO dto, Long userId);

	public CateringFacility getFacilityById(Long id);
	
	/**
	 * Returns top n CFs for each category
	 * 
	 * @param n
	 * @return
	 */
	public Map<Long, List<ReviewDTO>> getTopN(int n); 
	
	public List<ReviewDTO> getTopNByCategory(int n, long categoryId); 

}
