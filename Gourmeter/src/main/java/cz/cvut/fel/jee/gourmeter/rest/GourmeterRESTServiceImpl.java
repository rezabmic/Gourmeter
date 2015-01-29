package cz.cvut.fel.jee.gourmeter.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import cz.cvut.fel.jee.gourmeter.dto.CateringFacilityDTO;
import cz.cvut.fel.jee.gourmeter.dto.MarkerDTO;
import cz.cvut.fel.jee.gourmeter.ejb.FacilitySessionLocal;

@ApplicationScoped
public class GourmeterRESTServiceImpl implements GourmeterRESTService {
	@Inject
	FacilitySessionLocal service;

	public void createNewFacility(CateringFacilityDTO facility, Long userId) {
		this.service.createNewFacility(facility, userId);
	}

	public void addRecommendation(Long tagId, Long userId, Boolean recommended) {

	}

	// TODO
	public void testerApproval(Long userId, Boolean approved) {

	}

	public CateringFacilityDTO getFacilityById(Long id) {

	}

	// TODO
	public MarkerDTO facilitiesNearLocation(/* TODO */) {

	}

}
