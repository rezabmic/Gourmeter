package cz.cvut.fel.jee.gourmeter.rest;

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

import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.dto.CategoriesMapPositionWrapperDTO;
import cz.cvut.fel.jee.gourmeter.dto.CateringFacilityCreateDTO;
import cz.cvut.fel.jee.gourmeter.dto.CateringFacilityDTO;
import cz.cvut.fel.jee.gourmeter.dto.DTOUtils;
import cz.cvut.fel.jee.gourmeter.dto.MapPositionDTO;
import cz.cvut.fel.jee.gourmeter.dto.MarkerDTO;
import cz.cvut.fel.jee.gourmeter.dto.RecommendationDTO;
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
	//@RolesAllowed({ "user", "tester", "admin" })
	@PermitAll
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createNewFacility(CateringFacilityCreateDTO facility,
			@QueryParam("userId") Long userId) {
		try {
			facilitySession.createOrUpdateFacility(facility, userId);
		} catch (Exception e) {
			throw new InternalServerErrorException(e);
		}
		return Response.ok().build();
	}

	@Override
	@POST
	@Path("/recommendation")
	//@RolesAllowed({ "user", "tester", "admin" })
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response addRecommendation(RecommendationDTO recommendation) {
		Response response;
		
		try{
			this.dataSession.addRecommendation(recommendation.getTagId(), recommendation.getFacilityId(), recommendation.getUserId(), recommendation.isRecommended());
		 
			response = Response.ok().build();
		} catch(IllegalArgumentException ex){
			//tagId, facilityId or userId don't exist in DB. 
			response = Response.serverError().build();
		}
		
		return response;
	}

	@Override
	@POST
	@Path("/cateringFacility/update")
	@RolesAllowed({ "user", "tester", "admin" })
	@Produces(MediaType.APPLICATION_JSON)
	public Response testerApproval(CateringFacilityCreateDTO facility,
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
	@Path("/cateringFacility/byCategory/{id}/near")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public List<MarkerDTO> getFacilitiesNearLocationForCategory(@PathParam("id") Long categoryId, MapPositionDTO mapPos) {
		List<MarkerDTO> facilities = facilitySession.findFacilitiesInAreaByCategory(categoryId, mapPos);
		
		return facilities;
	}

	@Override
	@POST
	@Path("/cateringFacility/byCategories/near")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public List<MarkerDTO> getFacilitiesNearLocationForCategories(CategoriesMapPositionWrapperDTO wrapper) {
		List<MarkerDTO> facilities = facilitySession.findFacilitiesInAreaByCategories(wrapper.getCategories(), wrapper.getMapPos());
		
		return facilities;
	}
	
	@Override
	@POST
	@Path("/cateringFacility/near")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public List<MarkerDTO> facilitiesNearLocation(MapPositionDTO mapPos) {
		List<MarkerDTO> facilities = facilitySession.getFacilitiesInArea(mapPos);
		System.out.println(facilities.toString());
		return facilities;
	}

	@GET
	@Path("/whatsyourip")
	@PermitAll
	@Produces(MediaType.TEXT_PLAIN)
	public String testIp(@Context HttpServletRequest request) {

		return request.getRemoteAddr().toString();
	}
}
