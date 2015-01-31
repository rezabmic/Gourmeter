package cz.cvut.fel.jee.gourmeter.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import cz.cvut.fel.jee.gourmeter.dto.CategoryDTO;
import cz.cvut.fel.jee.gourmeter.dto.CateringFacilityDTO;
import cz.cvut.fel.jee.gourmeter.dto.MarkerDTO;
import cz.cvut.fel.jee.gourmeter.dto.TagDTO;
import cz.cvut.fel.jee.gourmeter.dto.UserDTO;

public interface GourmeterRESTService {

	public Response createNewFacility(CateringFacilityDTO facility, Long userId);

	public Response addRecommendation(Long facilityId, Long tagId, Long userId,
			Boolean recommended);

	public Response testerApproval(CateringFacilityDTO facility, Long userId,
			Boolean approved);

	public CateringFacilityDTO getFacilityById(Long id);

	public UserDTO getCurrentUser();

	// TODO zabezpeceni??? krome https, samozrejme
	public UserDTO signIn(String login, String password);

	public List<String> getAllTags();

	public List<TagDTO> getTagsForCategory(Long categoryId);

	public List<CategoryDTO> getAllCategories();

	public List<MarkerDTO> facilitiesNearLocation(double[] leftTopCorner,
			double[] rightBottomCorner);

}
