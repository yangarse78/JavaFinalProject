package il.ac.hit.packages;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class OpenWeatherMap implements IWeatherDataService  {

	
	
	String OPEN_WEATHER_MAP_URL = "http://api.openweathermap.org/data/2.5/weather";
	
	
	
	@Override
	public WeatherData getWeatherData(Location location) throws WeatherDataServiceException{
		HttpClient client = HttpClientBuilder.create().build();
		
		String city = location.getCityName();
        HttpGet request = new HttpGet(OPEN_WEATHER_MAP_URL + "?q=" + city);
        try {
            HttpResponse response = client.execute(request);
            System.out.println(response.getStatusLine());

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
            System.out.println(jsonResult);
            System.out.println(jsonResult.get("weather"));

            JSONObject main = (JSONObject) jsonResult.get("main");
            double temp = (double) main.get("temp");
            double maxTemp = (double) main.get("temp_max");
            double minTemp = (double) main.get("temp_min"); 
            WeatherData weatherData = new WeatherData(city, temp, maxTemp, minTemp);
            
            
            return weatherData;
            
        } catch (Exception e) {
           throw new WeatherDataServiceException("OpenWeatherMap: Failed to get weather data from open weather map service", e);

        }
	}

}
