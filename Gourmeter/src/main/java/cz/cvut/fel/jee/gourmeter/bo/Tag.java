package cz.cvut.fel.jee.gourmeter.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Jan Srogl
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Tag.findByName", query = "SELECT e FROM Tag e where e.name = :name"),
	@NamedQuery(name = "Tag.getAll", query = "SELECT e FROM Tag e")	
})
@Table(name = "tag",
		indexes = {@Index(name = "index_tag_name", columnList = "name", unique = true)})
public class Tag extends AbstractBusinessObject {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "name", nullable = false)
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
