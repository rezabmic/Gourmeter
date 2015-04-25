package cz.cvut.fel.jee.gourmeter.rest;

import java.util.List;
import java.util.Map;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import cz.cvut.fel.jee.gourmeter.dto.ReviewDTO;
import cz.cvut.fel.jee.gourmeter.ejb.FacilitySessionLocal;

@ApplicationScoped @Singleton
public class AsyncObserver {

	@Inject
	private FacilitySessionLocal facilitySession;
	
	/**
	 * CDI event observer which handles async requests
	 * 
	 * @param request
	 */
	public void onAsyncRequest(@Observes AsyncRequest request) {
		System.out.println("processRequest");
		
		//TODO generovani reportu
		Map<Long, List<ReviewDTO>> map = facilitySession.getTopN(request.getN());
		
		request.sendMessage(map);
	}	
}
