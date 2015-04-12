package cz.cvut.fel.jee.gourmeter.rest;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cz.cvut.fel.jee.gourmeter.bo.Tag;
import cz.cvut.fel.jee.gourmeter.dto.DTOUtils;
import cz.cvut.fel.jee.gourmeter.dto.TagDTO;
import cz.cvut.fel.jee.gourmeter.ejb.DataSessionLocal;

@Path("/tags")
@ApplicationScoped
public class TagRESTServiceImpl implements TagRESTService {

	@Inject
	private DataSessionLocal dataSession;

	@Override
	@GET
	@Path("/all")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public List<TagDTO> getAllTags() {
		List<Tag> tags = dataSession.getAllTags();
		
		return DTOUtils.getTagDTOList(tags);
	}

	@Override
	@GET
	@Path("/byCategory/{id}")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public List<TagDTO> getTagsByCategory(@PathParam("id") Long categoryId) {
		List<Tag> tags = this.dataSession.getTagsForCategory(categoryId);
		
		return DTOUtils.getTagDTOList(tags);
	}

	@Override
	@POST
	@Path("/byCategories")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public List<TagDTO> getTagsByCategories(List<Long> categories) {
		List<Tag> tags = this.dataSession.getTagsForCategories(categories);

		return DTOUtils.getTagDTOList(tags);
	}

}
