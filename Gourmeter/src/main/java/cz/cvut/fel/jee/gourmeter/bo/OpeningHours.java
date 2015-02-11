package cz.cvut.fel.jee.gourmeter.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jan Šrogl
 */
@Entity
@Table(name = "opening_hours")
public class OpeningHours extends AbstractBusinessObject {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private short dayNum;

	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date timeFrom;

	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date timeTo;

	@Temporal(TemporalType.TIME)
	@Column(nullable = true)
	private Date breakFrom;

	@Temporal(TemporalType.TIME)
	@Column(nullable = true)
	private Date breakTo;

	@ManyToOne
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
