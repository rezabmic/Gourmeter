package cz.cvut.fel.jee.gourmeter.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import cz.cvut.fel.jee.gourmeter.dto.CategoryDTO;
import cz.cvut.fel.jee.gourmeter.dto.CateringFacilityDTO;
import cz.cvut.fel.jee.gourmeter.dto.MarkerDTO;

public interface GourmeterRESTService {

	public Response createNewFacility(CateringFacilityDTO facility, Long userId);

	public Response addRecommendation(Long tagId, Long facilityId, Long userId,
			Boolean recommended);

	public Response testerApproval(CateringFacilityDTO facility, Long userId);

	public CateringFacilityDTO getFacilityById(Long id);

	public List<String> getAllTags();

	public List<String> getTagsForCategory(Long categoryId);

	public List<CategoryDTO> getAllCategories();

	public List<MarkerDTO> facilitiesNearLocation(double[] leftTopCorner,
			double[] rightBottomCorner);

}
