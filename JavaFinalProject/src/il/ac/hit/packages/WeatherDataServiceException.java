package il.ac.hit.packages;

public class WeatherDataServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WeatherDataServiceException() {
		super();
	}
	
	public WeatherDataServiceException(String message) {
		super(message);
		WeatherGUI.log.error(message);
	}

	public WeatherDataServiceException(String message, Throwable cause) {
        super(message, cause);
        WeatherGUI.log.error(message);
        WeatherGUI.log.error(cause.getStackTrace());
    }

	public WeatherDataServiceException(Throwable cause) {
        super(cause);
        WeatherGUI.log.error(cause.getStackTrace());
    }
}
