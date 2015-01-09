
package cz.cvut.fel.jee.gourmeter.bo;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Jan Šrogl
 */
@MappedSuperclass
public class AbstractBusinessObject implements Serializable
{   
    @Id
    @GeneratedValue(generator = "system-sequence")
    @GenericGenerator(name="system-sequence", strategy = "sequence")
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
