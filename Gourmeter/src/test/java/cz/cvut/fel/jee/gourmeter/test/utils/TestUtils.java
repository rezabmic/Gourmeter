package cz.cvut.fel.jee.gourmeter.test.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.EntityManager;

import cz.cvut.fel.jee.gourmeter.bo.Category;
import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.bo.Tag;
import cz.cvut.fel.jee.gourmeter.dto.AddressDTO;
import cz.cvut.fel.jee.gourmeter.dto.CateringFacilityDTO;
import cz.cvut.fel.jee.gourmeter.dto.MenuDTO;
import cz.cvut.fel.jee.gourmeter.dto.OpeningHoursDTO;
import cz.cvut.fel.jee.gourmeter.dto.TagDTO;

public class TestUtils {
	
	private static final DateFormat hmf = new SimpleDateFormat("hh:mm");

	private static final String TEST_STREET = "test_street";
	private static final String TEST_HOUSE_NUMBER = "test_house_number";
	public static final double TEST_LATITUDE = 45;
	public static final double TEST_LONGITUDE = 10;
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
		cf.setCategory(createCategory(em));
		cf.setCity(TEST_CITY);
		cf.setStreet(TEST_STREET);
		cf.setHouseNumber(TEST_HOUSE_NUMBER);
		cf.setCityDistrict(TEST_CITY_DISTRICT);
		cf.setCreator(null); // TODO
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
	
	public static CateringFacilityDTO createFacilityDTO() {
		AddressDTO a = new AddressDTO(TEST_CITY, TEST_STREET, TEST_HOUSE_NUMBER);
		MenuDTO m = new MenuDTO(getHours(11, 0), getHours(14, 30));
		OpeningHoursDTO o = getTestOpeningHoursDTO();
		
		CateringFacilityDTO cf = new CateringFacilityDTO();
		cf.setAddress(a);
		cf.setDescription(TEST_DECRIPTION);
		cf.setLatitude(TEST_LATITUDE);
		cf.setLongitude(TEST_LONGITUDE);
		cf.setMenu(m);
		cf.setTitle(TEST_FACILITY_NAME);
		cf.setUrl(TEST_URL);
		cf.setOpeningHours(Arrays.asList(o));
		
		TagDTO fish = new TagDTO("rybka", -1, -1);
		TagDTO dog = new TagDTO("pejsek", -1, -1);
		cf.setTags(Arrays.asList(fish, dog));
		
		return cf;
	}

	private static OpeningHoursDTO getTestOpeningHoursDTO() {
		OpeningHoursDTO o = new OpeningHoursDTO();
		o.setOpenFrom(hmf.format(getHours(7, 0)));
		o.setOpenTo(hmf.format(getHours(21, 0)));
		for (int i = 1; i <= 7; i++) {
			OpeningHoursDTO.Day d = new OpeningHoursDTO.Day();
			d.setDayNum((short) i);
			d.setSelected(true);
			o.getDays().add(d);
		}
		return o;
	}

	private static int getSerial() {
		return serial++;
	}
}
