package il.ac.hit.packages;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import sun.awt.IconInfo;




public class WeatherGUI {

	private WeatherData weatherData = new WeatherData();
	public static IWeatherDataService weatherService = null;
	private JFrame frame = null;
	private JButton button;

	private JPanel panelTop;
	private JPanel panelLeft;
	private JPanel panelCenter;
	
	private JTextField textField;
	
	private JLabel tempLabel;
	private JLabel locationLabel;
	private JLabel celsiusLabel;
	private JLabel iconLabel;
	
	
	public WeatherGUI(){
		frame = new JFrame("Weather");
		textField =  new JTextField(10);
		tempLabel = new JLabel();
		locationLabel = new JLabel();
		celsiusLabel = new JLabel();
		//iconLabel = new JLabel();
		
		button = new JButton("Check Weather");
		button.setEnabled(false);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(textField.getText().equals("")){
					button.setEnabled(false);
				}else{
					button.setEnabled(true);
				}
			}
		});
		
		frame = new JFrame("Weather Map");
		frame.getContentPane().setLayout(new FlowLayout());
		
		panelTop = new JPanel();
		panelTop.setLayout(new BorderLayout());
		panelTop.add(textField);
		panelTop.add(button, BorderLayout.EAST);
		panelTop.add(locationLabel, BorderLayout.SOUTH);
		
		panelLeft = new JPanel();
		panelLeft.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelLeft.setLayout(new BorderLayout());
		panelLeft.add(tempLabel, BorderLayout.WEST);
		panelLeft.add(celsiusLabel, BorderLayout.EAST);
		
		panelCenter = new JPanel();
		panelCenter.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelCenter.setLayout(new BorderLayout());
		//panelRight.add(iconLabel, BorderLayout.WEST);
		
		
		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());
		
		container.add("North", panelTop);
		container.add("West", panelLeft);
		container.add("Center", panelCenter);
		
		//adding listener
		button.addActionListener(new ActionListener(){	
				@Override
				public void actionPerformed(ActionEvent event) {
					
					String city = textField.getText();
					Location location = new Location(city);
					try{
						weatherService = WeatherDataServiceFactory.getWeatherDataService(WeatherDataServiceFactory.WeatherDataServiceType.OPEN_WEATHER_MAP);
						setWeatherData(weatherService.getWeatherData(location));
						locationLabel.setText("The current weather in " + weatherData.getCityName() + " is:");
						locationLabel.setFont(new Font("Arial", 2, 20));
						panelTop.add(locationLabel, BorderLayout.SOUTH);
						
						tempLabel.setText(weatherData.getTempStr());
						tempLabel.setFont(new Font("Arial", 1, 122));
						panelLeft.add(tempLabel, BorderLayout.WEST);
						celsiusLabel.setFont(celsiusLabel.getFont().deriveFont(102f));
						char c = '\u2103';
						celsiusLabel.setText(c+"");
						panelLeft.add(celsiusLabel, BorderLayout.EAST);
						
						Icon icon = new ImageIcon("http://openweathermap.org/img/w/" + weatherData.getIcon());
						URL url = new URL("http://openweathermap.org/img/w/" + weatherData.getIcon());
						BufferedImage wPic = ImageIO.read(url);
						iconLabel = new JLabel(new ImageIcon(wPic));
						panelCenter.add(iconLabel, BorderLayout.CENTER);
						
					}catch(WeatherDataServiceException e){
						System.out.println("Error in getting weather for " + city + "in WeatherGUI button listener");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		});
		
		
		
		//Closing windows listener
		frame.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent event) 
			{
				frame.setVisible(false);
				frame.dispose();
				System.exit(0);
			}
		});
	}

	public void go(){
		frame.setSize(700,400);
		frame.setVisible(true);	
	}
	
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				WeatherGUI demo = new WeatherGUI();
				demo.go();					
			}	
		});

	}

	public WeatherData getWeatherData() {
		return weatherData;
	}

	public void setWeatherData(WeatherData weatherData) {
		this.weatherData = weatherData;
	}
}
