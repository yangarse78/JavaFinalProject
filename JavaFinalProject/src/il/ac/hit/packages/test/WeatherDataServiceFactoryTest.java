package il.ac.hit.packages.test;

import static org.junit.Assert.*;

import org.junit.Test;

import il.ac.hit.packages.IWeatherDataService;
import il.ac.hit.packages.OpenWeatherMap;
import il.ac.hit.packages.WeatherDataServiceException;
import il.ac.hit.packages.WeatherDataServiceFactory;
import il.ac.hit.packages.WeatherDataServiceFactory.WeatherDataServiceType;
import il.ac.hit.packages.WeatherGUI;

public class WeatherDataServiceFactoryTest {

	

	@Test
	public final void testGetWeatherDataService() {
		
		IWeatherDataService actual = null;
		try {
			actual = WeatherDataServiceFactory.getWeatherDataService(WeatherDataServiceType.OPEN_WEATHER_MAP);
			
		} catch (WeatherDataServiceException e) {
			WeatherGUI.log.error("JUnit: Error in WeatherDataServiceFactoryTest.testGetWeatherDataService");
		}
		assertSame(OpenWeatherMap.getInstance(), actual);
	}
}
