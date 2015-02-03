package cz.cvut.fel.jee.gourmeter.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cz.cvut.fel.jee.gourmeter.bo.Category;
import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.bo.Tag;
import cz.cvut.fel.jee.gourmeter.dto.CategoryDTO;
import cz.cvut.fel.jee.gourmeter.dto.CateringFacilityDTO;
import cz.cvut.fel.jee.gourmeter.dto.DTOUtils;
import cz.cvut.fel.jee.gourmeter.dto.MapPositionDTO;
import cz.cvut.fel.jee.gourmeter.dto.MarkerDTO;
import cz.cvut.fel.jee.gourmeter.ejb.DataSessionLocal;
import cz.cvut.fel.jee.gourmeter.ejb.FacilitySessionLocal;

@Path("")
@ApplicationScoped
public class GourmeterRESTServiceImpl implements GourmeterRESTService {

	@Inject
	private FacilitySessionLocal facilitySession;

	@Inject
	private DataSessionLocal dataSession;

	@GET
	@Path("/hello")
	@RolesAllowed({ "user", "tester" })
	@Produces(MediaType.TEXT_PLAIN)
	public String testMethod() {
		return "testovaci metoda!!!";
	}

	@POST
	@Path("/cateringFacility")
	@RolesAllowed({ "user", "tester", "admin" })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createNewFacility(CateringFacilityDTO facility,
			@QueryParam("userId") Long userId) {
		try {
			facilitySession.createOrUpdateFacility(facility, userId);
		} catch (Exception e) {
			throw new InternalServerErrorException(e);
		}
		return Response.ok().build();
	}

	@Override
	@GET
	@Path("/recommendation/{tagId}/{facilityId}/{userId}/{recommended} ")
	@RolesAllowed({ "user", "tester", "admin" })
	@Produces(MediaType.APPLICATION_JSON)
	public Response addRecommendation(@PathParam("tagId") Long tagId,
			@PathParam("facilityId") Long facilityId,
			@PathParam("userId") Long userId,
			@PathParam("recommended") Boolean recommended) {
		this.dataSession.addRecommendation(tagId, facilityId, userId,
				recommended);
		return Response.ok().build();
	}

	@Override
	@POST
	@Path("/cateringFacility/update")
	@RolesAllowed({ "user", "tester", "admin" })
	@Produces(MediaType.APPLICATION_JSON)
	public Response testerApproval(CateringFacilityDTO facility,
			@QueryParam("userId") Long userId) {
		// TODO - kontrola usera
		this.facilitySession.createOrUpdateFacility(facility, userId);
		return Response.ok().build();
	}

	@Override
	@GET
	@Path("/cateringFacility/{id}")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public CateringFacilityDTO getFacilityById(@PathParam("id") Long id) {
		CateringFacility cf = this.facilitySession.getFacilityById(id);
		if (cf != null)
			return DTOUtils.getCateringFacilityDTO(cf);
		else {
			throw new NotFoundException("No such a facility with id: " + id);
		}
	}

	@Override
	@POST
	@Path("/cateringFacility/near")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public List<MarkerDTO> facilitiesNearLocation(MapPositionDTO mapPos) {
		return facilitySession.getFacilitiesInArea(mapPos);
	}

	@Override
	@GET
	@Path("/tags/all")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getAllTags() {
		List<Tag> list = this.dataSession.getAllTags();
		List<String> result = new ArrayList<>();
		for (int i = 0; i < result.size(); i++) {
			result.add(list.get(i).getName());
		}
		return result;
	}

	@Override
	@GET
	@Path("/tags/byCategory/{id}")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getTagsForCategory(@PathParam("id") Long categoryId) {
		List<Tag> list = this.dataSession.getTagsForCategory(categoryId);
		List<String> result = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			result.add(list.get(i).getName());
		}
		return result;
	}

	@Override
	@GET
	@Path("/category/all")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public List<CategoryDTO> getAllCategories() {
		List<Category> c = this.dataSession.getAllCategories();
		return DTOUtils.getListCategoryDTO(c);
	}

	@GET
	@Path("/whatsyourip")
	@PermitAll
	@Produces(MediaType.TEXT_PLAIN)
	public String testIp(@Context HttpServletRequest request) {

		return request.getRemoteAddr().toString();
	}
}
