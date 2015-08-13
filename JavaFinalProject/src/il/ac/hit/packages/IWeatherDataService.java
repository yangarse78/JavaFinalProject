package il.ac.hit.packages;

public interface IWeatherDataService {


	public WeatherData getWeatherData(Location location)  throws WeatherDataServiceException;
	
}
