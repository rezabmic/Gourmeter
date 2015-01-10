
package cz.cvut.fel.jee.gourmeter.bo;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author Jan Šrogl
 */
@Entity
public class Category extends AbstractBusinessObject
{
    @Column(nullable = false)
    String name;    
    @OneToMany(mappedBy = "category")
    Set<Tag> tags;
    
    //GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
    
}
