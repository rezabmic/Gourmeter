package cz.cvut.fel.jee.gourmeter.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jan Srogl
 */
@Entity
@Table(name = "opening_hours")
public class OpeningHours extends AbstractBusinessObject {

	private static final long serialVersionUID = 11160L;

	@NotNull
	private short dayNum;

	@Temporal(TemporalType.TIME)
	@NotNull
	private Date timeFrom;

	@Temporal(TemporalType.TIME)
	@NotNull
	private Date timeTo;

	@Temporal(TemporalType.TIME)
	private Date breakFrom;

	@Temporal(TemporalType.TIME)
	private Date breakTo;

	@ManyToOne(fetch=FetchType.LAZY)
	private CateringFacility facility;

	public short getDayNum() {
		return dayNum;
	}

	public void setDayNum(short dayNum) {
		this.dayNum = dayNum;
	}

	public Date getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(Date timeFrom) {
		this.timeFrom = timeFrom;
	}

	public Date getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(Date timeTo) {
		this.timeTo = timeTo;
	}

	public Date getBreakFrom() {
		return breakFrom;
	}

	public void setBreakFrom(Date breakFrom) {
		this.breakFrom = breakFrom;
	}

	public Date getBreakTo() {
		return breakTo;
	}

	public void setBreakTo(Date breakTo) {
		this.breakTo = breakTo;
	}

	public CateringFacility getFacility() {
		return facility;
	}

	public void setFacility(CateringFacility facility) {
		this.facility = facility;
	}

}
