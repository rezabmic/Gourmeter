
package cz.cvut.fel.jee.gourmeter.bo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Jan Šrogl
 */
@Entity
public class OpeningHours extends AbstractBusinessObject
{
    @Column(nullable = false)
    short dayNum;
    @Column(nullable = false)    
    Date timeFrom;
    @Column(nullable = false)
    Date timeTo;
    @Column(nullable = true)
    Date breakFrom;
    @Column(nullable = true)
    Date breakTo;
    
    @ManyToOne
    CateringFacility facility;
    
    //GETTERS & SETTERS
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
