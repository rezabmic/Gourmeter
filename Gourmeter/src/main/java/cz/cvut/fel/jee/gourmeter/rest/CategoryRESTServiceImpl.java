package cz.cvut.fel.jee.gourmeter.rest;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cz.cvut.fel.jee.gourmeter.bo.Category;
import cz.cvut.fel.jee.gourmeter.dto.CategoryDTO;
import cz.cvut.fel.jee.gourmeter.dto.DTOUtils;
import cz.cvut.fel.jee.gourmeter.ejb.DataSessionLocal;

@Path("/category")
@ApplicationScoped
public class CategoryRESTServiceImpl implements CategoryRESTService {

	@Inject
	private DataSessionLocal dataSession;

	@Override
	@GET
	@Path("/all")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public List<CategoryDTO> getAllCategories() {
		List<Category> c = this.dataSession.getAllCategories();
		return DTOUtils.getListCategoryDTO(c);
	}

}
