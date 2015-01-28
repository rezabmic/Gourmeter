package cz.cvut.fel.jee.gourmeter.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cz.cvut.fel.jee.gourmeter.bo.OpeningHours;

public class OpeningHoursDTO {

	private static final DateFormat df = new SimpleDateFormat("hh:mm");

	private final String openFrom;
	private final String openTo;
	private final String breakFrom;
	private final String breakTo;

	private final List<Day> days;

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

	public static DateFormat getDf() {
		return df;
	}

	public String getOpenFrom() {
		return openFrom;
	}

	public String getOpenTo() {
		return openTo;
	}

	public String getBreakFrom() {
		return breakFrom;
	}

	public String getBreakTo() {
		return breakTo;
	}

	public List<Day> getDays() {
		return days;
	}

}
