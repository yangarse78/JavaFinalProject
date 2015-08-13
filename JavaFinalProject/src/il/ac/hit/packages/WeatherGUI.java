package il.ac.hit.packages;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;




public class WeatherGUI {

	private WeatherData weatherData = null;
	private JFrame frame = null;
	private JPanel panelTop = null;
	private JPanel panelBottom = null;
	public static IWeatherDataService weatherService = null;
	
	
	
	public WeatherGUI (WeatherData data){

		frame = new JFrame("Currency Rate");
		panelTop = new JPanel();
		panelBottom = new JPanel();
		weatherData= data;
		
	}
	
	public void go(){
		frame.setSize(800,500);
		frame.setVisible(true);	
	}
	
	
	public static void main(String[] args) {
		
		try
		{
			Location location = new Location("Tel-Aviv");
			weatherService = WeatherDataServiceFactory.getWeatherDataService(WeatherDataServiceFactory.WeatherDataServiceType.OPEN_WEATHER_MAP);
			final WeatherData weatherData = weatherService.getWeatherData(location);
			
			
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					WeatherGUI demo = new WeatherGUI(weatherData);
					demo.go();					
				}	
			});
		}
		catch (Exception e)
		{
			
			e.printStackTrace();
		}

	}

}
