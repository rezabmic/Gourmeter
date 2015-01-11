package cz.cvut.fel.jee.gourmeter.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Jan Šrogl
 */
@Entity
@Table(name = "recommendation")
public class Recommendation extends AbstractBusinessObject {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private Boolean recommended;

	@ManyToOne
	private Tag tag;

	@ManyToOne
	private CateringFacility cateringFacility;

	@ManyToOne
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
