package cz.cvut.fel.jee.gourmeter.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jan Šrogl
 */
@Entity
@Table(name = "category")
public class Category extends AbstractBusinessObject {
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "category")
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
