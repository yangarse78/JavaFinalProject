package il.ac.hit.packages;

public class WeatherData {

	private String cityName;
	private double temp;
	private double maxTemp;
	private double minTemp;
	
	
	public WeatherData(String cityName, double temp, double maxTemp, double minTemp) {
		this.cityName = cityName;
		this.temp = temp;
		this.maxTemp = maxTemp;
		this.minTemp = minTemp;
	}


	public String getCityName() {
		return cityName;
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	public double getTemp() {
		return temp;
	}


	public void setTemp(double temp) {
		this.temp = temp;
	}


	public double getMaxTemp() {
		return maxTemp;
	}


	public void setMaxTemp(double maxTemp) {
		this.maxTemp = maxTemp;
	}


	public double getMinTemp() {
		return minTemp;
	}


	public void setMinTemp(double minTemp) {
		this.minTemp = minTemp;
	}
}
