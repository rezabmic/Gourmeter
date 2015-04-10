package cz.cvut.fel.jee.gourmeter.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Jan Srogl
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Category.findById", query = "SELECT e FROM Category e where e.id = :id"),
		@NamedQuery(name = "Category.getAll", query = "SELECT e FROM Category e") })
@Table(name = "category")
public class Category extends AbstractBusinessObject {
	private static final long serialVersionUID = 11180L;

	@NotEmpty	
	private String name;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Tag> tags;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Tag> getTags() {
		if (tags == null) {
			tags = new ArrayList<>();
		}
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

}
