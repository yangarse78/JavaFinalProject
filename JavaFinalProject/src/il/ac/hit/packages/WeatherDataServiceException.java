package il.ac.hit.packages;

public class WeatherDataServiceException extends Exception {
	
	public WeatherDataServiceException() {
		super();
	}
	
	public WeatherDataServiceException(String message) {
		super(message);
	}

	public WeatherDataServiceException(String message, Throwable cause) {
        super(message, cause);
    }

	public WeatherDataServiceException(Throwable cause) {
        super(cause);
    }
}
