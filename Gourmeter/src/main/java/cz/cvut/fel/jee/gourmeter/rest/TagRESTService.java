package cz.cvut.fel.jee.gourmeter.rest;

import java.util.List;

import cz.cvut.fel.jee.gourmeter.dto.TagDTO;

public interface TagRESTService {

	public List<TagDTO> getAllTags();

	public List<TagDTO> getTagsByCategory(Long categoryId);
	
	public List<TagDTO> getTagsByCategories(List<Long> categories);

}
