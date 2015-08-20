package il.ac.hit.packages;

public class WeatherData {

	private String cityName;
	private int temp;
	private String tempStr;
	private int maxTemp;
	private int minTemp;
	private String icon;
	private long humidity;
	private String humidityStr;
	private int windSpeed;
	private String weatherDescription;
	private String pressure;
	
	public WeatherData(){}
	
	public WeatherData(String cityName, double temp, double maxTemp, double minTemp, String icon, 
										long humidity, int windSpeed, String weatherDescription, String pressure) {
		this.cityName = cityName;
		this.temp = (int)temp;
		this.tempStr = String.valueOf((int)temp);
		this.maxTemp = (int)maxTemp;
		this.minTemp = (int)minTemp;
		this.icon = icon;
		this.humidity = humidity;
		this.humidityStr = String.valueOf((int)humidity);
		this.windSpeed = windSpeed;
		this.weatherDescription = weatherDescription;
		this.pressure = pressure;
	}


	
	public String getPressure() {
		return pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	public String getWeatherDescription() {
		return weatherDescription;
	}

	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}

	public int getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(int windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getCityName() {
		return cityName;
	}
	
	public long getHumidity() {
		return humidity;
	}

	public String getHumidityStr() {
		return humidityStr;
	}

	public void setHumidity(long humidity) {
		this.humidity = humidity;
		this.humidityStr = String.valueOf((int)humidity);
	}

	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	public int getTemp() {
		return temp;
	}

	public String getTempStr() {
		return tempStr;
	}

	public void setTemp(double temp) {
		this.temp = (int)temp;
		this.tempStr = String.valueOf((int)temp);
	}


	public int getMaxTemp() {
		return maxTemp;
	}


	public void setMaxTemp(double maxTemp) {
		this.maxTemp = (int)maxTemp;
	}


	public int getMinTemp() {
		return minTemp;
	}


	public void setMinTemp(double minTemp) {
		this.minTemp = (int)minTemp;
	}
}
