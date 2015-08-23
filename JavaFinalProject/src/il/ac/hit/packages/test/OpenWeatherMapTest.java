package il.ac.hit.packages.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import il.ac.hit.packages.Location;
import il.ac.hit.packages.OpenWeatherMap;
import il.ac.hit.packages.WeatherData;
import il.ac.hit.packages.WeatherDataServiceException;
import il.ac.hit.packages.WeatherGUI;

public class OpenWeatherMapTest {

	public OpenWeatherMap owm;

	@Before
	public void setUp() throws Exception {
		owm =  OpenWeatherMap.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		owm = null;
	}

	@Test
	public final void testGetWeatherData() {
		WeatherData wd = null;
		try {
			wd = owm.getWeatherData(new Location("London"));
		} catch (WeatherDataServiceException e) {
			WeatherGUI.log.error("JUnit: Error in OpenWeatherMapTest.testGetWeatherData");
		}
		assertNotNull(wd);
	}
	
	@Test
	public final void testGetWeatherDataDigitError() {
		Location loc = new Location("sds222");
		WeatherData wd = null;
		try {
			wd = owm.getWeatherData(loc);
		} catch (WeatherDataServiceException e) {
			WeatherGUI.log.error("JUnit: Error in OpenWeatherMapTest.testGetWeatherDataDigitError");
		}
		assertNotNull(wd);
	}

}
