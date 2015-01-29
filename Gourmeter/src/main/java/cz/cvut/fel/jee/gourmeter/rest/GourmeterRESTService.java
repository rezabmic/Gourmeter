package cz.cvut.fel.jee.gourmeter.rest;

import cz.cvut.fel.jee.gourmeter.dto.CateringFacilityDTO;
import cz.cvut.fel.jee.gourmeter.dto.MarkerDTO;

public interface GourmeterRESTService {

	public void createNewFacility(CateringFacilityDTO facility, Long userId);

	public void addRecommendation(	Long facilityId,
									Long tagId,
									Long userId,
									Boolean recommended);

	// TODO
	public void testerApproval(Long userId, Boolean approved);

	public CateringFacilityDTO getFacilityById(Long id);

	// TODO
	public MarkerDTO facilitiesNearLocation(/* TODO */);

}
