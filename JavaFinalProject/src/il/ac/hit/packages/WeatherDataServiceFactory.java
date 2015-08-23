package il.ac.hit.packages;

public class WeatherDataServiceFactory {

	public static enum WeatherDataServiceType{
		OPEN_WEATHER_MAP
	}
	
	
	public static IWeatherDataService getWeatherDataService (WeatherDataServiceType serviceType) throws WeatherDataServiceException{
		
		IWeatherDataService service = null;
		switch (serviceType){
			case OPEN_WEATHER_MAP:
		        service = OpenWeatherMap.getInstance();
		        break;
			default:
			    throw new WeatherDataServiceException("There is no service");
			
		}
		
		return service;
	}
}
