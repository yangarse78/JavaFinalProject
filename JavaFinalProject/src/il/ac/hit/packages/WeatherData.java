package il.ac.hit.packages;

public class WeatherData {

	private String cityName;
	private int temp;
	private String tempStr;
	private int maxTemp;
	private int minTemp;
	private String icon;
	
	public WeatherData(){}
	
	public WeatherData(String cityName, double temp, double maxTemp, double minTemp, String icon) {
		this.cityName = cityName;
		this.temp = (int)temp;
		this.tempStr = String.valueOf((int)temp);
		this.maxTemp = (int)maxTemp;
		this.minTemp = (int)minTemp;
		this.icon = icon;
	}


	public String getCityName() {
		System.out.println("kk");
		return cityName;
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
