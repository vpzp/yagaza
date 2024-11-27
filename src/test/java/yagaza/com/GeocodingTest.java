package yagaza.com;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import yagaza.com.hotel.Hotel;
import yagaza.com.hotel.HotelService;
import yagaza.com.restaurant.Restaurant;
import yagaza.com.restaurant.RestaurantService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class GeocodingTest {
    private String apiKey = "f27879a6408379d53c8d6135e7422e59";
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private HotelService hotelService;

    @Test
    public void setRestaurantMap(){
        restaurantService.setAllMap();
    }

    @Test
    public void setHotelMap(){
        hotelService.setAllMap();
    }
}
