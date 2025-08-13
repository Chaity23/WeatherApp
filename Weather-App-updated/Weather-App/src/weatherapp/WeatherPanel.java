package weatherapp;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
public class WeatherPanel extends JPanel{
    BufferedImage cloudyBackground, rainBackground, sunBackground,
            thunderBackground, clearBackground, humidBackground, choice_Back;
    BufferedImage sunny, lightRain, heavyRain, humidOvercast, cloudy, thunder, clear, choice_Icon;


    String city, temp, condition;

    public WeatherPanel(String city, String temp, String condition) throws IOException {
        cloudyBackground = ImageIO.read(getClass().getResource("/partlycloudy.jpg"));
        rainBackground = ImageIO.read(getClass().getResource("/rainy.jpg"));
        sunBackground = ImageIO.read(getClass().getResource("/sunny.jpg"));
        thunderBackground = ImageIO.read(getClass().getResource("/thunderstorm.jpg"));
        clearBackground = ImageIO.read(getClass().getResource("/clearskies.jpg"));
        humidBackground = ImageIO.read(getClass().getResource("/humidovercast.jpg"));
        sunny = ImageIO.read(getClass().getResource("/sunny.png"));
        lightRain = ImageIO.read(getClass().getResource("/lightrain.png"));
        heavyRain = ImageIO.read(getClass().getResource("/heavyrain.png"));
        humidOvercast = ImageIO.read(getClass().getResource("/humidovercast.png"));
        cloudy = ImageIO.read(getClass().getResource("/partlycloudy.png"));
        thunder = ImageIO.read(getClass().getResource("/thunderstorm.png"));
        clear = ImageIO.read(getClass().getResource("/clearskies.png"));

        this.city = city;
        this.temp = temp;
        this.condition = condition;

    }

    public void draw() {
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        switch(condition){
            case "Clouds":
                g.drawImage(cloudyBackground, 0, 0, Main.ScreenWidth, Main.ScreenHeight, null);
                g.drawImage(cloudy, 140, 300, 100, 100, null);
                break;

            case "Thunderstorm":
                g.drawImage(thunderBackground, 0, 0, Main.ScreenWidth, Main.ScreenHeight, null);
                g.drawImage(thunder, 100, 100, 100, 100, null);
                break;

            case "Rain":
                g.drawImage(rainBackground, 0, 0, Main.ScreenWidth, Main.ScreenHeight, null);
                g.drawImage(heavyRain, 140, 300, 100, 100, null);
                break;

            case "Clear":
                g.drawImage(clearBackground , 0, 0, Main.ScreenWidth, Main.ScreenHeight, null);
//
//                g.drawImage(sunBackground, 0, 0, Main.ScreenWidth, Main.ScreenHeight, null);
                g.drawImage(sunny, 140, 300, 100, 100, null);
                break;

            case "Mist":
            case "Smoke":
            case "Haze":
            case "Dust":
            case "Fog":
            case "Sand":
            case "Ash":
            case "Squall":
            case "Tornado":


                g.drawImage(humidBackground, 0, 0, Main.ScreenWidth, Main.ScreenHeight, null);
                g.drawImage(humidOvercast , 140, 300, 100, 100, null);
                break;

            case "Drizzle":
                g.drawImage(rainBackground, 0, 0, Main.ScreenWidth, Main.ScreenHeight, null);
                g.drawImage(lightRain, 140, 300, 100, 100, null);
                break;

//            case "Clear":
//                g.drawImage(	clearBackground , 0, 0, Main.ScreenWidth, Main.ScreenHeight, null);
//                g.drawImage(clear, 140, 300, 100, 100, null);
//                break;








        }

    }
}