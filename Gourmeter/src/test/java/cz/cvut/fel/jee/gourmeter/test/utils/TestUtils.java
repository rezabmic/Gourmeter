package cz.cvut.fel.jee.gourmeter.test.utils;

import java.util.Date;

import javax.persistence.EntityManager;

import cz.cvut.fel.jee.gourmeter.bo.Category;
import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.bo.Tag;

public class TestUtils {

	public static final int TEST_LATITUDE = 45;
	public static final int TEST_LONGITUDE = 10;
	public static final String TEST_URL = "http://google.com";
	public static final String TEST_FACILITY_NAME = "U testovaciho vycepu";
	public static final String TEST_DECRIPTION = "test decription";
	public static final String TEST_CITY_DISTRICT = "test city district";
	public static final String TEST_CITY = "test city";
	public static final Date TEST_INSTANT = new Date();
	public static int serial = 0;

	public static Tag createTag(EntityManager em) {
		Tag t = new Tag();
		t.setName("test tag" + getSerial());
		em.persist(t);
		return t;
	}

	public static Category createCategory(EntityManager em) {
		Category c = new Category();
		c.setName("test category" + getSerial());

		Tag t1 = new Tag();
		Tag t2 = new Tag();
		t1.setName("test category tag" + getSerial());
		t2.setName("test category tag" + getSerial());
		t1.setCategory(c);
		t2.setCategory(c);

		em.persist(c);
		em.persist(t1);
		em.persist(t2);

		return c;
	}

	public static CateringFacility createCateringFacility(EntityManager em) {
		CateringFacility cf = new CateringFacility();
		cf.setAdditionConfirmed(true);
		cf.setDateOfConfirmation(TEST_INSTANT);
		cf.setCategory(createCategory(em));
		cf.setCity(TEST_CITY);
		cf.setCityDistrict(TEST_CITY_DISTRICT);
		cf.setCreator(null); // TODO
		cf.setDateOfConfirmation(null); // TODO
		cf.setDescription(TEST_DECRIPTION);
		cf.setLatitude(TEST_LATITUDE);
		cf.setLongitude(TEST_LONGITUDE);
		cf.setMenuFrom(getHours(11, 0));
		cf.setMenuTo(getHours(14, 30));
		cf.setName(TEST_FACILITY_NAME);
		cf.setUrl(TEST_URL);

		em.persist(cf);
		return cf;
	}

	private static Date getHours(int hours, int minutes) {
		Date d = new Date();
		d.setHours(hours);
		d.setMinutes(minutes);
		
		// hibernate pry zatim jdk 8 time api nepodporuje 
//		LocalTime lt = LocalTime.of(hours, minutes);
//		Instant i = lt.atDate(LocalDate.of(0, 0, 0))
//				.atZone(ZoneId.systemDefault()).toInstant();
//		Date time = Date.from(i);
//		return time;
		return d;
	}

	private static int getSerial() {
		return serial++;
	}
}
