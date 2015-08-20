package il.ac.hit.packages;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import org.apache.log4j.Logger;




public class WeatherGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String PERSENT_SIGN = "%";
	private final String SPEED_MEASUREMENT = " km/h";
	private final String PREASURE_MEASUREMENT =" hPa";
	
	private WeatherData weatherData = new WeatherData();
	public static IWeatherDataService weatherService = null;
	
	static Logger log = Logger.getLogger(WeatherGUI.class);
	
	private Font basicFont;
	private JFrame frame = null;
	private JButton button;
	private JTextField textField;
	private JLabel jLabel1;
	private JLabel tempLabel;
	private JLabel locationLabel;
	private JLabel celsiusLabel;
	private JLabel iconLabel;
	private JLabel humidityLabel;	
	private JLabel minMaxTempLabel;	
	private JLabel dateLabel;
	private JLabel windLabel;
    private JLabel pressureLabel;
	
	public WeatherGUI(){
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setBackground(new Color(0, 255, 204));
		setSize(563, 300);
		setTitle("Weather Service");
		
		textField =  new JTextField();
		button = new JButton("Check Weather");
		button.setBackground(new java.awt.Color(204, 204, 255));
		jLabel1 = new JLabel("Please type city name:");
		jLabel1.setFont(new Font("Times New Roman", 1, 20));
		jLabel1.setForeground(new Color(0, 102, 102));
		tempLabel = new JLabel();
		locationLabel = new JLabel();
		celsiusLabel = new JLabel();
		iconLabel = new JLabel();
		humidityLabel = new JLabel();
		minMaxTempLabel = new JLabel();
		dateLabel = new JLabel();
		windLabel = new JLabel();
		pressureLabel = new JLabel();
		
		
		
		GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(textField, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(button, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(locationLabel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(dateLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tempLabel, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(celsiusLabel, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                            .addComponent(iconLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(humidityLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(minMaxTempLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(windLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pressureLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup( Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(button))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(dateLabel, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(locationLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(tempLabel, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(celsiusLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(iconLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(humidityLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(minMaxTempLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(windLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(pressureLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
		
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
		
		
		//adding listener
		button.addActionListener(new ActionListener(){	
				@Override
				public void actionPerformed(ActionEvent event) {
					
					basicFont = new Font("Arial", 0, 16);
					String city = textField.getText();
					Location location = new Location(city);
					try{
						weatherService = WeatherDataServiceFactory.getWeatherDataService(WeatherDataServiceFactory.WeatherDataServiceType.OPEN_WEATHER_MAP);
						setWeatherData(weatherService.getWeatherData(location));
						
						locationLabel.setText(weatherData.getCityName());
						locationLabel.setFont(new Font("Script MT Bold", 1, 20));
						locationLabel.setForeground(new Color(255, 102, 102));
						
						dateLabel.setText(getCurrentDateStr());
						dateLabel.setFont(new Font("Script MT Bold", 1, 20));
						dateLabel.setForeground(new Color(255, 102, 102));
						
						tempLabel.setText(weatherData.getTempStr());
						tempLabel.setFont(new Font("Arial", 1, 122));
						tempLabel.setForeground(Color.BLUE);
						
						celsiusLabel.setFont(celsiusLabel.getFont().deriveFont(102f));
						char c = '\u2103';
						celsiusLabel.setText(c+"");
						celsiusLabel.setForeground(Color.BLUE);
						
						URL url = new URL("http://openweathermap.org/img/w/" + weatherData.getIcon());
						BufferedImage wPic = ImageIO.read(url);
						
						iconLabel.setIcon(new ImageIcon(wPic));
						iconLabel.setText(weatherData.getWeatherDescription());
						iconLabel.setFont(basicFont);
						iconLabel.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
						
						humidityLabel.setText("Humidity: " + weatherData.getHumidityStr() + PERSENT_SIGN);
						humidityLabel.setFont(basicFont);
						humidityLabel.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

						minMaxTempLabel.setText("Min " + weatherData.getMinTemp() + c + "" + "     Max " + weatherData.getMaxTemp() + c + "");
						minMaxTempLabel.setFont(celsiusLabel.getFont().deriveFont(18f));
						minMaxTempLabel.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
						
						windLabel.setText("Wind: " + weatherData.getWindSpeed() + SPEED_MEASUREMENT);
						windLabel.setFont(basicFont);
						windLabel.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
				        
						pressureLabel.setText("Pressure: " + weatherData.getPressure() + PREASURE_MEASUREMENT);
						pressureLabel.setFont(basicFont);
						pressureLabel.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
						
					}catch(WeatherDataServiceException e){
						log.error(e.getStackTrace());
						String errMsg = e.getMessage();
						if( errMsg == null || errMsg.equals("")){
							errMsg = "Error in getting weather for " + city + ".";
						}
						JOptionPane.showMessageDialog(frame, errMsg);
					} catch (MalformedURLException e) {
						log.error(e.getStackTrace());
						String errMsg = e.getMessage();
						if( errMsg == null || errMsg.equals("")){
							errMsg = "Error in getting weather for " + city + ".";
						}
						JOptionPane.showMessageDialog(frame, errMsg);						
					} catch (IOException e) {
						log.error(e.getStackTrace());
						String errMsg = e.getMessage();
						if( errMsg == null || errMsg.equals("")){
							errMsg = "Error in getting weather for " + city + ".";
						}
						JOptionPane.showMessageDialog(frame, errMsg);
					}
				}
		});
	}

	private String getCurrentDateStr(){
		SimpleDateFormat sdfDate = new SimpleDateFormat("MMMM  HH:mm a");
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}
	
	public static void main(String[] args) {
		
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
        	log.error(WeatherGUI.class.getName(), ex);
        } catch (InstantiationException ex) {
        	log.error(WeatherGUI.class.getName(), ex);
        } catch (IllegalAccessException ex) {
        	log.error(WeatherGUI.class.getName(), ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        	log.error(WeatherGUI.class.getName(), ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WeatherGUI().setVisible(true);
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
