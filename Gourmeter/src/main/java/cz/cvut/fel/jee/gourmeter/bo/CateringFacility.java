
package cz.cvut.fel.jee.gourmeter.bo;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Jan Šrogl
 */
@Entity
public class CateringFacility extends AbstractBusinessObject
{
    @Column(nullable = false)
    String name;
    @Column(nullable = true)
    String popis;
    @ManyToOne
    Category category;
    @Column(nullable = false)    
    String url;
    @Column(nullable = false)
    String city;
    @Column(nullable = true)
    String cityDistrict;
    @Column(nullable = false)    
    String coordinates;
    @ManyToOne    
    User creator;
    @Column(nullable = false)    
    Boolean additionConfirmed;
    @Column(nullable = false)    
    Date dateOfConfirmation;
    @OneToMany
    Set<Tag> tags;
    @Column(nullable = true)    
    Date menuFrom;
    @Column(nullable = true)    
    Date menuTo;
    @Column(nullable = true)    
    String menuUrl;    
    
    @OneToMany
    Set<OpeningHours> openingHours;
    
    //GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityDistrict() {
        return cityDistrict;
    }

    public void setCityDistrict(String cityDistrict) {
        this.cityDistrict = cityDistrict;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Boolean getAdditionConfirmed() {
        return additionConfirmed;
    }

    public void setAdditionConfirmed(Boolean additionConfirmed) {
        this.additionConfirmed = additionConfirmed;
    }

    public Date getDateOfConfirmation() {
        return dateOfConfirmation;
    }

    public void setDateOfConfirmation(Date dateOfConfirmation) {
        this.dateOfConfirmation = dateOfConfirmation;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Date getMenuFrom() {
        return menuFrom;
    }

    public void setMenuFrom(Date menuFrom) {
        this.menuFrom = menuFrom;
    }

    public Date getMenuTo() {
        return menuTo;
    }

    public void setMenuTo(Date menuTo) {
        this.menuTo = menuTo;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Set<OpeningHours> getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(Set<OpeningHours> openingHours) {
        this.openingHours = openingHours;
    }
    
}
