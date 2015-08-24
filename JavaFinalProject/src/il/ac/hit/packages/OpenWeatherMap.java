package il.ac.hit.packages;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class OpenWeatherMap implements IWeatherDataService  {

	private static OpenWeatherMap instance = null;
	private final String OPEN_WEATHER_MAP_URL = "http://api.openweathermap.org/data/2.5/weather";
	private final double KELVIN_DEF =  273.15;
	private final String ICON_FILE_EXTENTION = ".png";
	
	
	public static OpenWeatherMap getInstance(){
		if(instance == null){
	        synchronized (OpenWeatherMap.class) {
	            if(instance == null){
	                instance = new OpenWeatherMap();
	            }
	        }
	    }
        return instance;
    }
	
	@Override
	public WeatherData getWeatherData(Location location) throws WeatherDataServiceException{
		HttpClient client = HttpClientBuilder.create().build();
		
		String city = location.getCityName();
        try {
        	HttpGet request = new HttpGet(OPEN_WEATHER_MAP_URL + "?q=" + city);
            HttpResponse response = client.execute(request);

            InputStreamReader in = new InputStreamReader(response.getEntity().getContent());
            BufferedReader rd = new BufferedReader(in);
            
            JSONParser jsonParser = new JSONParser();
            String line = new String();
            StringBuilder res = new StringBuilder();
            while((line = rd.readLine()) != null){
            	res.append(line);
            }
            
            in.close();
            
            JSONObject jsonResult = (JSONObject) jsonParser.parse(res.toString());
            String errorMsg = (String)jsonResult.get("message");
            if(errorMsg != null){
            	throw new WeatherDataServiceException(errorMsg);
            }
           
            WeatherGUI.log.info(jsonResult);

            String cityName = (String)jsonResult.get("name");
            JSONObject main = (JSONObject) jsonResult.get("main");
            double temp = (double) main.get("temp");
            double maxTemp = (double) main.get("temp_max");
            double minTemp = (double) main.get("temp_min"); 
            long humidity = (long)main.get("humidity");
            String pressure = String.valueOf((long)main.get("pressure"));
            JSONArray weatherArr = (JSONArray) jsonResult.get("weather");
            JSONObject weatherCondition = (JSONObject) weatherArr.get(0);
            JSONObject wind =  (JSONObject) jsonResult.get("wind");
            double windSpeed = (double)wind.get("speed");
            String icon = (String) weatherCondition.get("icon");
            String weatherDescription =  (String) weatherCondition.get("description");
            WeatherData weatherData = new WeatherData(cityName, temp-KELVIN_DEF, maxTemp-KELVIN_DEF, minTemp-KELVIN_DEF, icon + ICON_FILE_EXTENTION, 
            																						humidity, meterSecToKmHour(windSpeed), weatherDescription, pressure);
            return weatherData;
            
        } catch (Exception e) {
           throw new WeatherDataServiceException(e);
        }
	}
	
	private int meterSecToKmHour(double windSpeed){
		return (int)((windSpeed*18)/5);
	}

}
