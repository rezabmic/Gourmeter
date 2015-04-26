package cz.cvut.fel.jee.gourmeter.bo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jan Srogl
 */
@Entity
@Table(name = "recommendation")
public class Recommendation extends AbstractBusinessObject {

	private static final long serialVersionUID = 11150L;

	@NotNull
	private Boolean recommended;

	@ManyToOne
	private Tag tag;

	@ManyToOne(fetch=FetchType.LAZY)
	private CateringFacility cateringFacility;

	@ManyToOne(fetch=FetchType.LAZY)
	private User user;

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
