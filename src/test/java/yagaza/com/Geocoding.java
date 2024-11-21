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
public class Geocoding {
    private String apiKey = "f27879a6408379d53c8d6135e7422e59";
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private HotelService hotelService;

    @Test
    public void setRestaurantMap(){
        List<Restaurant> restaurantList = restaurantService.getRestaurantList();
        for (Restaurant restaurant : restaurantList) {
            String title = restaurant.getName();
            Map<String, Double> map = fecthMap(title);
            restaurantService.setMap(restaurant, map.get("x"), map.get("y"));
        }
    }

    @Test
    public void setHotelMap(){
        List<Hotel> hotelList = hotelService.getHotelList();
        for (Hotel hotel : hotelList) {
            String title = hotel.getHotelName();
            Map<String, Double> map = fecthMap(title);
            hotelService.setMap(hotel, map.get("x"), map.get("y"));
        }
    }

    public Map<String, Double> fecthMap(String title) {
        // API 호출 URL
        String url = "https://dapi.kakao.com/v2/local/search/keyword.json?query=" + title;
        Map<String, Double> xyMap = new HashMap<>();

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        // API 호출
        ResponseEntity<String> response = restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, entity, String.class);

        try {
            // JSON 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode documents = root.path("documents");

            if (documents.isArray() && documents.size() > 0) {
                JsonNode firstDoc = documents.get(0);
                Double x = Double.valueOf(firstDoc.path("x").asText());
                Double y = Double.valueOf(firstDoc.path("y").asText());

                xyMap.put("x", x);
                xyMap.put("y", y);
            } else {
                System.out.println("No results found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xyMap;
    }
}
