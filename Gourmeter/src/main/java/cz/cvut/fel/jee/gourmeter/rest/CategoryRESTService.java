package cz.cvut.fel.jee.gourmeter.rest;

import java.util.List;

import cz.cvut.fel.jee.gourmeter.dto.CategoryDTO;

public interface CategoryRESTService {

	public List<CategoryDTO> getAllCategories();

}
