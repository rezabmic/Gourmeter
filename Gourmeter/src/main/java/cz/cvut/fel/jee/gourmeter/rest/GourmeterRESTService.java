package cz.cvut.fel.jee.gourmeter.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import cz.cvut.fel.jee.gourmeter.dto.CategoryDTO;
import cz.cvut.fel.jee.gourmeter.dto.CateringFacilityCreateDTO;
import cz.cvut.fel.jee.gourmeter.dto.CateringFacilityDTO;
import cz.cvut.fel.jee.gourmeter.dto.MapPositionDTO;
import cz.cvut.fel.jee.gourmeter.dto.MarkerDTO;

public interface GourmeterRESTService {

	public Response createNewFacility(CateringFacilityCreateDTO facility, Long userId);

	public Response addRecommendation(Long tagId, Long facilityId, Long userId,
			Boolean recommended);

	public Response testerApproval(CateringFacilityCreateDTO facility, Long userId);

	public CateringFacilityDTO getFacilityById(Long id);

	public List<String> getAllTags();

	public List<String> getTagsForCategory(Long categoryId);

	public List<CategoryDTO> getAllCategories();

	/**
	 * Returns map markers.
	 * 
	 * @param position
	 * @return
	 */
	public List<MarkerDTO> facilitiesNearLocation(MapPositionDTO position);

}
