package yagaza.com;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import yagaza.com.hotel.Hotel;
import yagaza.com.hotel.HotelService;
import yagaza.com.restaurant.Restaurant;
import yagaza.com.restaurant.RestaurantService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Geocoding {
    private String apiKey = "f27879a6408379d53c8d6135e7422e59";

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
