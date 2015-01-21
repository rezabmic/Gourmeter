package cz.cvut.fel.jee.gourmeter.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

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

}
