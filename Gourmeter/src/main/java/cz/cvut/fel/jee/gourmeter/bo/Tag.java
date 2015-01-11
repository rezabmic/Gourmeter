
package cz.cvut.fel.jee.gourmeter.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Jan Šrogl
 */
@Entity
public class Tag extends AbstractBusinessObject
{
    @Column(nullable = false)
    String nazev;    
    @ManyToOne
    Category category;
    
    //GETTERS & SETTERS
    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
}
