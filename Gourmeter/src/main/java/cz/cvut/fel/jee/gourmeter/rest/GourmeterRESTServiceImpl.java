package cz.cvut.fel.jee.gourmeter.rest;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cz.cvut.fel.jee.gourmeter.bo.Category;
import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.bo.Tag;
import cz.cvut.fel.jee.gourmeter.dto.CategoryDTO;
import cz.cvut.fel.jee.gourmeter.dto.CateringFacilityDTO;
import cz.cvut.fel.jee.gourmeter.dto.DTOUtils;
import cz.cvut.fel.jee.gourmeter.dto.MarkerDTO;
import cz.cvut.fel.jee.gourmeter.dto.TagDTO;
import cz.cvut.fel.jee.gourmeter.dto.UserDTO;
import cz.cvut.fel.jee.gourmeter.ejb.DataSessionLocal;
import cz.cvut.fel.jee.gourmeter.ejb.FacilitySessionLocal;
import cz.cvut.fel.jee.gourmeter.ejb.UserSessionLocal;
import cz.cvut.fel.jee.gourmeter.exception.SignInException;

@Path("")
@ApplicationScoped
public class GourmeterRESTServiceImpl implements GourmeterRESTService {

	@Inject
	private FacilitySessionLocal facilitySession;

	@Inject
	private UserSessionLocal userSession;

	@Inject
	private DataSessionLocal dataSession;

	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String testMethod() {
		return "testovaci metoda!!!";
	}

	@POST
	@Path("/cateringFacility")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createNewFacility(CateringFacilityDTO facility,
			@QueryParam("userId") Long userId) {
		try {
			facilitySession.createNewFacility(facility, userId);
		} catch (Exception e) {
			throw new InternalServerErrorException(e);
		}
		return Response.ok().build();
	}

	@Override
	public Response addRecommendation(Long facilityId, Long tagId, Long userId,
			Boolean recommended) {
		// TODO Auto-generated method stub

		return Response.ok().build();
	}

	@Override
	public Response testerApproval(CateringFacilityDTO facility, Long userId,
			Boolean approved) {
		// TODO Auto-generated method stub

		return Response.ok().build();
	}

	@Override
	@GET
	@Path("/cateringFacility/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public CateringFacilityDTO getFacilityById(@PathParam("id") Long id) {
		CateringFacility cf = this.facilitySession.getFacilityById(id);
		if (cf != null)
			return DTOUtils.getCateringFacilityDTO(cf);
		else {
			// TODO - tady to chce vymyslet nejaky navratovy kod
			// neco jako: return Response.noContent().build();
			return null;
		}
	}

	@Override
	public MarkerDTO facilitiesNearLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO signIn(String login, String password) {
		try {
			return userSession.authenticateUser(login, password);
		} catch (SignInException e) {
			throw new BadRequestException("Bad credentials");
		}
	}

	@Override
	@GET
	@Path("/tags/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getAllTags() {
		List<Tag> list = this.dataSession.getAllTags();
		List<String> result = new ArrayList<>();
		for (int i = 0; i < result.size(); i++) 
		{
			result.add(list.get(i).getName());			
		}
		return result;
	}

	@Override
	@GET
	@Path("/tags/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TagDTO> getTagsForCategory(@PathParam("id") Long categoryId) {
		List<Tag> list = this.dataSession.getTagsForCategory(categoryId);
		return DTOUtils.getTagDTOList(list);
	}

	@Override
	@GET
	@Path("/category/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CategoryDTO> getAllCategories() {
		List<Category> c = this.dataSession.getAllCategories();
		return DTOUtils.getListCategoryDTO(c);
	}

}
