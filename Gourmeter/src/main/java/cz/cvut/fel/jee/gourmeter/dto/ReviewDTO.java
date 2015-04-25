package cz.cvut.fel.jee.gourmeter.dto;

import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;

public class ReviewDTO {
	private CateringFacility cf;
	private long total;
	
	public ReviewDTO(CateringFacility cf, long total) {
		this.cf = cf;
		this.total = total;
	}

	public CateringFacility getCf() {
		return cf;
	}

	public void setCf(CateringFacility cf) {
		this.cf = cf;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	
}
