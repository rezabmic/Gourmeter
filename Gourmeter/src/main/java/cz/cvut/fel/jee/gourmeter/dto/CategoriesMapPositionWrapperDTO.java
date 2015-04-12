package cz.cvut.fel.jee.gourmeter.dto;

import java.util.List;

public class CategoriesMapPositionWrapperDTO{
	private List<Long> categories;
	private MapPositionDTO mapPos;
	
	public CategoriesMapPositionWrapperDTO() {
		// TODO Auto-generated constructor stub
	}

	public List<Long> getCategories() {
		return categories;
	}

	public void setCategories(List<Long> categories) {
		this.categories = categories;
	}

	public MapPositionDTO getMapPos() {
		return mapPos;
	}

	public void setMapPos(MapPositionDTO mapPos) {
		this.mapPos = mapPos;
	}
	
	
}