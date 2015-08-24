package il.ac.hit.packages;

public class WeatherServiceController {

	private static IWeatherDataService weatherService = null;
	
	public static WeatherData getWeatherData(Location loc) throws WeatherDataServiceException{
		WeatherData wd = null;
		try {
			weatherService = WeatherDataServiceFactory.getWeatherDataService(WeatherDataServiceFactory.WeatherDataServiceType.OPEN_WEATHER_MAP);
			wd = weatherService.getWeatherData(loc);
		} catch (WeatherDataServiceException e) {
			throw e;
		}
		return wd;
	}
}
