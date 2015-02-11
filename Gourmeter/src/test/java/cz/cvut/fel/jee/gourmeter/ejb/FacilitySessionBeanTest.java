package cz.cvut.fel.jee.gourmeter.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import cz.cvut.fel.jee.gourmeter.bo.Category;
import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.test.utils.ArchiveMaker;
import cz.cvut.fel.jee.gourmeter.test.utils.TestUtils;

@RunWith(Arquillian.class)
@Transactional(value = TransactionMode.ROLLBACK)
public class FacilitySessionBeanTest {

	@EJB
	private FacilitySessionLocal facilitySession;

	@PersistenceContext(unitName = "GourmeterPU")
	private EntityManager em;

	@Deployment
	public static WebArchive createArchive() {
		return ArchiveMaker.createTestArchive();
	}

	@Test
	public void testFindFacilityByGPS() {
		CateringFacility cf = TestUtils.createCateringFacility(em);
		em.flush();
		em.clear();

		List<CateringFacility> cfs = facilitySession.getFacilitiesInArea(
				TestUtils.TEST_LATITUDE, TestUtils.TEST_LONGITUDE);

		assertNotNull(cfs);
		assertFalse(cfs.isEmpty());
		assertEquals(cfs.get(0), cf);
	}
	
	//TODO
	/*@Test
	public void testCreateFacility() {
		
		facilitySession.createOrUpdateFacility(TestUtils.createFacilityDTO(), 0L);
		em.flush();
		em.clear();
		
		TypedQuery<CateringFacility> q = em.createNamedQuery("CateringFacility.findByName", CateringFacility.class);
		q.setParameter("name", TestUtils.TEST_FACILITY_NAME);
		List<CateringFacility> resultList = q.getResultList();
		assertEquals(1, resultList.size());
		
		CateringFacility cf = resultList.get(0);
		assertEquals(7, cf.getOpeningHours().size());
		assertEquals(2, cf.getTags().size());
		assertEquals(0, cf.getRecommendations().size());
	}*/

}
