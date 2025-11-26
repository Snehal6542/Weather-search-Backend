package com.example.weather_search.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class WeatherService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final HashMap<String, CachedItem> cache = new HashMap<>();

    private final long CACHE_EXPIRY = 5 * 60 * 1000; // 5 min
    private final String API_KEY = "2c1c0eb2ddb3594479245a4c1970df6f";

    public Object getWeather(String city) {

        // Cache hit
        if (cache.containsKey(city)) {
            CachedItem item = cache.get(city);
            if (System.currentTimeMillis() - item.timestamp < CACHE_EXPIRY) {
                return item.data;
            }
        }

        // Fetch from API
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" 
                     + city + "&appid=" + API_KEY + "&units=metric";

        Object response = restTemplate.getForObject(url, Object.class);

        // Save in cache
        cache.put(city, new CachedItem(response));

        return response;
    }

    static class CachedItem {
        public long timestamp;
        public Object data;

        public CachedItem(Object data) {
            this.timestamp = System.currentTimeMillis();
            this.data = data;
        }
    }
}
