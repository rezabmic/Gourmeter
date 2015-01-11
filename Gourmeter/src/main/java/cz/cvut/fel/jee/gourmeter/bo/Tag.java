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
@Table(name = "tag")
public class Tag extends AbstractBusinessObject {
	
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;

	@ManyToOne
	private Category category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
