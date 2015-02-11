package cz.cvut.fel.jee.gourmeter.ejb;

import static org.junit.Assert.*;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@Transactional(value = TransactionMode.ROLLBACK)
public class DataSessionBeanTest {
	
	@Deployment
	public static WebArchive createWar() {
		return ShrinkWrap.create(WebArchive.class, "test.war");
	}

	@Test
	public void test() {
		System.out.println("Testujem s Arquillianem!");
	}

}
