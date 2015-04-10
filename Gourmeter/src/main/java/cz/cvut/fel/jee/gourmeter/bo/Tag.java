package cz.cvut.fel.jee.gourmeter.bo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	
	private static final long serialVersionUID = 11140L;

	@NotNull
	private String name;

	@ManyToOne(fetch=FetchType.LAZY)
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
