package cz.cvut.fel.jee.gourmeter.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MenuDTO {
	
	private static final DateFormat df = new SimpleDateFormat("hh:mm");

	private final String from;
	private final String to;
	
	public MenuDTO(Date menuFrom, Date menuTo) {
		from = df.format(menuFrom);
		to = df.format(menuTo);
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

}
