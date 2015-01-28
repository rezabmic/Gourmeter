package cz.cvut.fel.jee.gourmeter.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.bo.Recommendation;
import cz.cvut.fel.jee.gourmeter.bo.Tag;

public class DTOUtils {

	public static List<TagDTO> getFacilityTags(CateringFacility f) {
		Map<Long, TagDTO> tagsMap = new HashMap<>();
		for (Recommendation r : f.getRecommendations()) {
			Tag tag = r.getTag();
			tagsMap.put(tag.getId(), new TagDTO(tag.getName(), r.getRecommended(),
					f.getAdditionConfirmed()));
		}

		for (Tag tag : f.getTags()) {
			if (!tagsMap.containsKey(tag.getId())) {
				tagsMap.put(tag.getId(), new TagDTO(tag.getName(), false, false));
			}
		}
		
		return new ArrayList<>(tagsMap.values());
	}
}
