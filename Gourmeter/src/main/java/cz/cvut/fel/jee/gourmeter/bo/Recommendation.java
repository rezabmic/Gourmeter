
package cz.cvut.fel.jee.gourmeter.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Jan Šrogl
 */
@Entity
public class Recommendation extends AbstractBusinessObject
{
    @ManyToOne
    Tag tag;
    @ManyToOne
    CateringFacility cateringFacility;
    @ManyToOne
    User user;    
    @Column(nullable = false)
    Boolean recommended;

    //GETTERS & SETTERS
    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public CateringFacility getCateringFacility() {
        return cateringFacility;
    }

    public void setCateringFacility(CateringFacility cateringFacility) {
        this.cateringFacility = cateringFacility;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getRecommended() {
        return recommended;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }

}
