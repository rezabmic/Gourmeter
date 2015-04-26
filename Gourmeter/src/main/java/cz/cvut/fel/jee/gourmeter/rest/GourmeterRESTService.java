package cz.cvut.fel.jee.gourmeter.rest;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import cz.cvut.fel.jee.gourmeter.dto.CategoriesMapPositionWrapperDTO;
import cz.cvut.fel.jee.gourmeter.dto.CateringFacilityCreateDTO;
import cz.cvut.fel.jee.gourmeter.dto.CateringFacilityDTO;
import cz.cvut.fel.jee.gourmeter.dto.MapPositionDTO;
import cz.cvut.fel.jee.gourmeter.dto.MarkerDTO;
import cz.cvut.fel.jee.gourmeter.dto.RecommendationDTO;

public interface GourmeterRESTService {

	/**
	 * TODO description
	 * 
	 * @param facility
	 * @param userId
	 * @return
	 */
	public Response createNewFacility(CateringFacilityCreateDTO facility, Long userId);

	/**
	 * TODO description
	 * 
	 * @param recommendation
	 * @return
	 */
	public Response addRecommendation(RecommendationDTO recommendation);

	/**
	 * TODO description
	 * 
	 * @param facility
	 * @param userId
	 * @return
	 */
	public Response testerApproval(CateringFacilityCreateDTO facility, Long userId);

	/**
	 * TODO description
	 * 
	 * @param id
	 * @return
	 */
	public CateringFacilityDTO getFacilityById(Long id);

	/**
	 * TODO description
	 * 
	 * @param categoryId
	 * @param mapPos
	 * @return
	 */
	public List<MarkerDTO> getFacilitiesNearLocationForCategory(@PathParam("id") Long categoryId, MapPositionDTO mapPos);
	
	/**
	 * 
	 * @param wrapper
	 * @return	
	 */
	public List<MarkerDTO> getFacilitiesNearLocationForCategories(CategoriesMapPositionWrapperDTO wrapper);
	
	/**
	 * Returns map markers.
	 * 
	 * @param position
	 * @return
	 */
	public List<MarkerDTO> facilitiesNearLocation(MapPositionDTO position);

}
