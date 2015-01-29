package cz.cvut.fel.jee.gourmeter.rest;

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
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cz.cvut.fel.jee.gourmeter.dto.CategoryDTO;
import cz.cvut.fel.jee.gourmeter.dto.CateringFacilityDTO;
import cz.cvut.fel.jee.gourmeter.dto.MarkerDTO;
import cz.cvut.fel.jee.gourmeter.dto.TagDTO;
import cz.cvut.fel.jee.gourmeter.dto.UserDTO;
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

	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String testMethod() {
		return "testovaci metoda!!!";
	}

	@POST
	@Path("/cateringFacility")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createNewFacility(CateringFacilityDTO facility, Long userId) {
		try {
			facilitySession.createNewFacility(facility, userId);
		} catch (Exception e) {
			throw new InternalServerErrorException(e);
		}
		return Response.ok().build();
	}

	@Override
	public Response addRecommendation(	Long facilityId,
										Long tagId,
										Long userId,
										Boolean recommended) {
		// TODO Auto-generated method stub

		return Response.ok().build();
	}

	@Override
	public Response testerApproval(	CateringFacilityDTO facility,
									Long userId,
									Boolean approved) {
		// TODO Auto-generated method stub

		return Response.ok().build();
	}

	@Override
	@GET
	@Path("/cateringFacility")
	@Produces(MediaType.APPLICATION_JSON)
	public CateringFacilityDTO getFacilityById(@PathParam("id") Long id) {
		// TODO Auto-generated method stub
		return null;
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
	public List<TagDTO> getAllTags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TagDTO> getTagsForCategory(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GET
	@Path("/category/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CategoryDTO> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

}
