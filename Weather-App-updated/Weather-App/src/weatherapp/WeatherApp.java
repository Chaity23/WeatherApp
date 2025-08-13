package weatherapp;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
public class WeatherApp extends JPanel {
    BufferedImage homeBackground;
    Font merriweather;

    public WeatherApp() throws IOException, FontFormatException {
        homeBackground = ImageIO.read(Objects.requireNonNull(getClass().getResource("/homebackground.jpg")));
        setSize(Main.ScreenWidth, Main.ScreenHeight);
        merriweather = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().
                getResourceAsStream("/Merriweatherfont.ttf")));
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(merriweather);
    }
    public void draw() {
        repaint();    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(homeBackground, 0, 0, Main.ScreenWidth, Main.ScreenHeight, null);
    }


    public void showWeatherFrame(String district, String temp, String condition) throws IOException {
        JFrame weatherFrame = new JFrame("Weather Info - " + district);
        weatherFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        WeatherPanel weatherPanel = new WeatherPanel(district, temp, condition);
        weatherPanel.setPreferredSize(new Dimension(Main.ScreenWidth, Main.ScreenHeight));
        weatherPanel.setLayout(new BorderLayout());

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel districtLabel = new JLabel(district);
        districtLabel.setForeground(Color.WHITE);
        districtLabel.setFont(merriweather.deriveFont(Font.BOLD, 30f));
        districtLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        String tempStr = Integer.toString( (int) (Double.parseDouble(temp) ) );
        JLabel tempLabel = new JLabel(tempStr+"°");
        tempLabel.setForeground(Color.WHITE);
        tempLabel.setFont(merriweather.deriveFont(Font.BOLD, 24f));
        tempLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel conditionLabel = new JLabel(condition);
        conditionLabel.setForeground(Color.WHITE);
        conditionLabel.setFont(merriweather.deriveFont(Font.BOLD, 24f));
        conditionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        textPanel.add(districtLabel);
        textPanel.add(Box.createVerticalStrut(10));
        textPanel.add(tempLabel);
        textPanel.add(Box.createVerticalStrut(10));
        textPanel.add(conditionLabel);

        JPanel container = new JPanel(new GridBagLayout());
        container.setOpaque(false);
        container.add(textPanel);

        weatherPanel.add(container, BorderLayout.CENTER);

        weatherFrame.add(weatherPanel);

        weatherFrame.setSize(Main.ScreenWidth, Main.ScreenHeight);
        weatherFrame.setLocationRelativeTo(null);
        weatherFrame.setVisible(true);
    }



}