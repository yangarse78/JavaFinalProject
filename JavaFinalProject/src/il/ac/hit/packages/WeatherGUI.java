package il.ac.hit.packages;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import sun.awt.IconInfo;




public class WeatherGUI {

	private final String PERSENT_SIGN = "%";
	
	
	private WeatherData weatherData = new WeatherData();
	public static IWeatherDataService weatherService = null;
	private Font basicFont;
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
	private JLabel humidityLabel;	
	private JLabel minMaxTempLabel;	
	
	public WeatherGUI(){
		frame = new JFrame("Weather");

		textField =  new JTextField(10);
		tempLabel = new JLabel();
		
		
		//locationLabel = new JLabel();
		//celsiusLabel = new JLabel();
		
		
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
		//panelTop.add(locationLabel, BorderLayout.SOUTH);
		
		panelLeft = new JPanel();
		panelLeft.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelLeft.setLayout(new BorderLayout());
		panelLeft.add(tempLabel, BorderLayout.WEST);
		//panelLeft.add(celsiusLabel, BorderLayout.EAST);
		
		panelCenter = new JPanel();
		panelCenter.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelCenter.setLayout(new GridLayout(3,1));
		//panelRight.add(iconLabel, BorderLayout.WEST);
		
		
		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());
		container.setBackground(Color.RED); 
		
		container.add("North", panelTop);
		container.add("West", panelLeft);
		container.add("Center", panelCenter);
		
		//adding listener
		button.addActionListener(new ActionListener(){	
				@Override
				public void actionPerformed(ActionEvent event) {
					
					basicFont = new Font("Arial", 3, 20);
					String city = textField.getText();
					Location location = new Location(city);
					try{
						weatherService = WeatherDataServiceFactory.getWeatherDataService(WeatherDataServiceFactory.WeatherDataServiceType.OPEN_WEATHER_MAP);
						setWeatherData(weatherService.getWeatherData(location));
						
						locationLabel = new JLabel();
						locationLabel.setText(weatherData.getCityName() + "     " + getCurrentDateStr() );
						locationLabel.setFont(basicFont);
						panelTop.add(locationLabel, BorderLayout.SOUTH);
						
						tempLabel.setText(weatherData.getTempStr());
						tempLabel.setFont(new Font("Arial", 1, 122));
						tempLabel.setForeground(Color.BLUE);
						panelLeft.add(tempLabel, BorderLayout.WEST);
						celsiusLabel = new JLabel();
						celsiusLabel.setFont(celsiusLabel.getFont().deriveFont(102f));
						char c = '\u2103';
						celsiusLabel.setText(c+"");
						celsiusLabel.setForeground(Color.BLUE);
						panelLeft.add(celsiusLabel, BorderLayout.EAST);
						
						URL url = new URL("http://openweathermap.org/img/w/" + weatherData.getIcon());
						BufferedImage wPic = ImageIO.read(url);
						iconLabel = new JLabel(new ImageIcon(wPic));
						panelCenter.add(iconLabel);
						humidityLabel = new JLabel();
						humidityLabel.setText("Humidity: " + weatherData.getHumidityStr() + PERSENT_SIGN);
						humidityLabel.setFont(basicFont);
						panelCenter.add(humidityLabel);
						minMaxTempLabel = new JLabel();
						minMaxTempLabel.setText("Min " + weatherData.getMinTemp() + c + "" + "     Max " + weatherData.getMaxTemp() + c + "");
						minMaxTempLabel.setFont(celsiusLabel.getFont().deriveFont(18f));
						panelCenter.add(minMaxTempLabel);
						
						
					}catch(WeatherDataServiceException e){
						String errMsg = e.getMessage();
						if( errMsg == null || errMsg.equals("")){
							errMsg = "Error in getting weather for " + city + "in WeatherGUI button listener.";
						}
						JOptionPane.showMessageDialog(frame, errMsg);
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
	
	
	private String getCurrentDateStr(){
		SimpleDateFormat sdfDate = new SimpleDateFormat("MMMM  HH:mm a");
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
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
