package cz.cvut.fel.jee.gourmeter.dto;

import java.io.Serializable;

public class MapPositionDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Double latitudeTop;
	private Double longitudeTop;
	private Double latitudeBottom;
	private Double longitudeBottom;

	public MapPositionDTO() {
	}

	public Double getLatitudeTop() {
		return latitudeTop;
	}

	public void setLatitudeTop(Double latitudeTop) {
		this.latitudeTop = latitudeTop;
	}

	public Double getLongitudeTop() {
		return longitudeTop;
	}

	public void setLongitudeTop(Double longitudeTop) {
		this.longitudeTop = longitudeTop;
	}

	public Double getLatitudeBottom() {
		return latitudeBottom;
	}

	public void setLatitudeBottom(Double latitudeBottom) {
		this.latitudeBottom = latitudeBottom;
	}

	public Double getLongitudeBottom() {
		return longitudeBottom;
	}

	public void setLongitudeBottom(Double longitudeBottom) {
		this.longitudeBottom = longitudeBottom;
	}

}
