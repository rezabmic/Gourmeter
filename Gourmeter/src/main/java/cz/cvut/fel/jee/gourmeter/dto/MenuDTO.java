package cz.cvut.fel.jee.gourmeter.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MenuDTO {

	private static final DateFormat df = new SimpleDateFormat("HH:mm");

	private String from;
	private String to;
	private String url;

	public MenuDTO() {
	}

	public MenuDTO(Date menuFrom, Date menuTo, String url) {
		from = df.format(menuFrom);
		to = df.format(menuTo);
		this.url = url;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
