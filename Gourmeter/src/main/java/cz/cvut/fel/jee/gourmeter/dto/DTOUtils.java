package cz.cvut.fel.jee.gourmeter.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.cvut.fel.jee.gourmeter.bo.Category;
import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.bo.Recommendation;
import cz.cvut.fel.jee.gourmeter.bo.Tag;

public class DTOUtils {

	public static List<TagDTO> getFacilityTags(CateringFacility f) {
		Map<Long, TagReccommendations> recommendationsMap = new HashMap<>();
		for (Recommendation r : f.getRecommendations()) {
			Tag tag = r.getTag();
			TagReccommendations tr = recommendationsMap.get(tag.getId());
			if (tr == null) {
				tr = new TagReccommendations();
				recommendationsMap.put(tag.getId(), tr);
			}
			if (r.getRecommended()) {
				tr.incRecommended();
				tr.incTotal();
			} else if (!r.getRecommended()) {
				tr.incTotal();
			} else {
				// TODO budem s tim vubec neco delat?
			}
		}

		List<TagDTO> dtos = new ArrayList<>();
		for (Tag tag : f.getTags()) {
			TagReccommendations tr = recommendationsMap.get(tag.getId());
			dtos.add(new TagDTO(tag.getName(), tr.getRecommended(), tr.getTotal()));
		}
		return dtos;

	}

	public static CategoryDTO getCategoryDTO(Category category) {
		if (category == null)
			return null;
		else {
			CategoryDTO dto = new CategoryDTO();
			dto.setId(category.getId());
			dto.setName(category.getName());
			return dto;
		}
	}

	public static CateringFacilityDTO
			getCateringFacilityDTO(CateringFacility facility) {
		if (facility != null)
			return new CateringFacilityDTO(facility);
		else
			return null;
	}

	public static TagDTO getTagDTO(Tag tag, Integer recommended,
									Integer reviewed) {
		if (tag == null)
			return null;
		else {
			return new TagDTO(tag, recommended, reviewed);
		}
	}

	public static List<CategoryDTO> getListCategoryDTO(List<Category> list) {
		List<CategoryDTO> result = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			result.add(DTOUtils.getCategoryDTO(list.get(i)));
		}
		return result;
	}

	public static List<TagDTO> getTagDTOList(List<Tag> tags) {
		// TODO
		List<TagDTO> result = new ArrayList<>();
		for (int i = 0; i < tags.size(); i++) {
			result.add(DTOUtils.getTagDTO(tags.get(i), 0, 0));
		}
		return result;
	}
	
	
	private static class TagReccommendations {
		
		private int recommended;
		private int total;
		
		private TagReccommendations() {
			recommended = 0;
			total = 0;
		}
		
		public void incRecommended() {
			recommended += 1;
		}
		
		public void incTotal() {
			total += 1;
		}

		public int getRecommended() {
			return recommended;
		}

		public int getTotal() {
			return total;
		}
		
	}
}
