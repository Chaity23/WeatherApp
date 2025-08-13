package weatherapp;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.swing.*;
public class Main {
    public static int ScreenWidth = 450, ScreenHeight = 500;
    public static void main(String[] args) throws IOException, FontFormatException {
        JFrame frame = new JFrame();

        frame.setTitle("Weekly Weather - 7 Districts of Bangladesh");
        frame.setSize(ScreenWidth, ScreenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setLayout(null);

        WeatherApp weatherApp = new WeatherApp();
        weatherApp.setLayout(new BorderLayout());

        // Panel to hold combo box and button vertically
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Transparent panel
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        weatherApp.draw();


        String[] city = {"Dhaka", "Chittagong", "Sylhet", "Rajshahi", "Khulna", "Barishal", "Rangpur"};

        // Create combo box for districts
        JComboBox<String> comboBox = new JComboBox<>();

        // Purple shade for main box
        Color purpleShade = new Color(147, 112, 219); // Medium purple
        // Sky blue for dropdown list
        Color skyBlue = new Color(135, 206, 235);

        // Set main combo box appearance
        comboBox.setOpaque(true);
        comboBox.setBackground(purpleShade);
        comboBox.setForeground(Color.WHITE);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setMaximumSize(new Dimension(250, 35));
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Populate combo box with district names
        for (String district : city) {
            comboBox.addItem(district);
        }

        // Custom renderer for dropdown list items
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (isSelected) {
                    c.setBackground(skyBlue.darker()); // Darker sky blue when selected
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(skyBlue); // Sky blue background
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        // Create the "Check" button
        JButton button = new JButton("Check");
        button.setPreferredSize(new Dimension(120, 35));
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(skyBlue);

        // Add vertical space, then add combo box and button to panel
        buttonPanel.add(Box.createVerticalStrut(150));
        buttonPanel.add(comboBox);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(button);

        weatherApp.add(buttonPanel, BorderLayout.CENTER);
        frame.add(weatherApp);

        // Button action: when clicked, open a new window with weather info of selected district
        button.addActionListener(e -> {
            frame.dispose(); // Close main window

//            WeatherInfo info = weather.get(comboBox.getSelectedItem());

            try {
                String citySelected = Objects.requireNonNull(comboBox.getSelectedItem()).toString();
                String json = FetchWeather.getWeatherJson(citySelected);
                FetchWeather.WeatherData data = FetchWeather.WeatherParser.parse(json);

                weatherApp.showWeatherFrame(citySelected, String.valueOf(data.main.temp), data.weather[0].main);

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Show main frame
        frame.setVisible(true);
    }
}
