package weatherapp;
import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class FetchWeather {
    private static final String WEATHER_API = "7f23c2e13b6c6d5f4bbbe91e78485f32";



    public static String getWeatherJson(String city) throws Exception {
        String endpoint = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + WEATHER_API + "&units=metric";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new RuntimeException("Failed to get weather data: " + response.statusCode());
        }
    }

    class WeatherData {
        Main main;
        Weather[] weather;

        class Main {
            float temp;
            int humidity;
        }

        class Weather {
            String main;
            String description;
        }
    }

    public class WeatherParser {
        public static WeatherData parse(String json) {
            Gson gson = new Gson();
            return gson.fromJson(json, WeatherData.class);
        }
    }

}

