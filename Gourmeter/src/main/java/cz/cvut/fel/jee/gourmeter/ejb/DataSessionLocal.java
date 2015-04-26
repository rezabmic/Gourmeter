package cz.cvut.fel.jee.gourmeter.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import cz.cvut.fel.jee.gourmeter.bo.Category;
import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.bo.Tag;
import cz.cvut.fel.jee.gourmeter.bo.User;
import cz.cvut.fel.jee.gourmeter.bo.UserRole;

@Local
public interface DataSessionLocal {

	public CriteriaBuilder getCriteriaBuilder();

	public <T> List<T> executeCriteriaQuery(CriteriaQuery<T> cq);

	public List<CateringFacility> findFacilitiesByGPSAndTag(
			CoordinateSearchWrapper csw, Tag tag);

	public List<Tag> getAllTags();

	public List<Tag> getTagsForCategory(Long id);
	
	public List<Tag> getTagsForCategories(List<Long> categories);

	public List<Category> getAllCategories();

	public void addRecommendation(Long tagId, Long facilityId, Long userId,
			Boolean recommended);
}
