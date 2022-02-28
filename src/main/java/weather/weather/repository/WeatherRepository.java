package weather.weather.repository;

import weather.weather.domain.Weather;

import java.util.HashMap;
import java.util.Map;

public class WeatherRepository {
    private static Map<String, Integer> store = new HashMap<>();


    public void save(Weather weather){
        store.put(weather.getLocation(), weather.getTemp());
    }

    public Integer findTemp(String location){
        return store.get(location);
    }
}
