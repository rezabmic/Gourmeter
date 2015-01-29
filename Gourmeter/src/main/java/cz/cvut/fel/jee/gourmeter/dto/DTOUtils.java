package cz.cvut.fel.jee.gourmeter.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.cvut.fel.jee.gourmeter.bo.Category;
import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.bo.Recommendation;
import cz.cvut.fel.jee.gourmeter.bo.Tag;

public class DTOUtils {

	public static List<TagDTO> getFacilityTags(CateringFacility f) {
		Map<Long, TagDTO> tagsMap = new HashMap<>();
		for (Recommendation r : f.getRecommendations()) {
			Tag tag = r.getTag();
			tagsMap.put(
					tag.getId(),
					new TagDTO(tag.getName(), r.getRecommended(), f
							.getAdditionConfirmed()));
		}

		for (Tag tag : f.getTags()) {
			if (!tagsMap.containsKey(tag.getId())) {
				tagsMap.put(tag.getId(),
						new TagDTO(tag.getName(), false, false));
			}
		}

		return new ArrayList<>(tagsMap.values());
	}

	public static CategoryDTO getCategoryDTO(Category category) {
		// TODO
		return null;
	}

	public static TagDTO getTagDTO(Tag tag) {
		// TODO
		return null;
	}

	public static List<CategoryDTO> getListCategoryDTO(List<Category> list) {
		List<CategoryDTO> result = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			result.add(DTOUtils.getCategoryDTO(list.get(i)));
		}
		return result;
	}

	public static List<TagDTO> getTagDTOList(List<Tag> tags) {
		List<TagDTO> result = new ArrayList<>();
		for (int i = 0; i < tags.size(); i++) {
			result.add(DTOUtils.getTagDTO(tags.get(i)));
		}
		return result;
	}
}
