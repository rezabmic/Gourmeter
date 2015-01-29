package cz.cvut.fel.jee.gourmeter.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cz.cvut.fel.jee.gourmeter.bo.OpeningHours;

public class OpeningHoursDTO {

	private static final DateFormat df = new SimpleDateFormat("hh:mm");

	private String openFrom;
	private String openTo;
	private String breakFrom;
	private String breakTo;

	private List<Day> days;

	public OpeningHoursDTO() {
	}
	
	public OpeningHoursDTO(OpeningHours o) {
		openFrom = df.format(o.getTimeFrom());
		openTo = df.format(o.getTimeTo());
		breakFrom = df.format(o.getBreakFrom());
		breakTo = df.format(o.getBreakTo());

		this.days = new ArrayList<>();
		days.add(new Day(o.getDayNum(), false));
	}

	public static class Day {

		private short dayNum;
		private Boolean selected;

		public Day() {
		}

		public Day(short dayNum) {
			this.dayNum = dayNum;
		}

		public Day(short dayNum, Boolean selected) {
			this.dayNum = dayNum;
			this.selected = selected;
		}

		public short getDayNum() {
			return dayNum;
		}

		public void setDayNum(short dayNum) {
			this.dayNum = dayNum;
		}

		public Boolean getSelected() {
			return selected;
		}

		public void setSelected(Boolean selected) {
			this.selected = selected;
		}
	}

	public String getOpenFrom() {
		return openFrom;
	}

	public void setOpenFrom(String openFrom) {
		this.openFrom = openFrom;
	}

	public String getOpenTo() {
		return openTo;
	}

	public void setOpenTo(String openTo) {
		this.openTo = openTo;
	}

	public String getBreakFrom() {
		return breakFrom;
	}

	public void setBreakFrom(String breakFrom) {
		this.breakFrom = breakFrom;
	}

	public String getBreakTo() {
		return breakTo;
	}

	public void setBreakTo(String breakTo) {
		this.breakTo = breakTo;
	}

	public List<Day> getDays() {
		if (days == null) {
			days = new ArrayList<>();
		}
		return days;
	}

	public void setDays(List<Day> days) {
		this.days = days;
	}


}
