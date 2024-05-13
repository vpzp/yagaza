package yagaza.com.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public void create(String name, String content, String type, String img, int[] price, List<String> openTime){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setContent(content);
        restaurant.setType(type);
        restaurant.setImg(img);
        restaurant.setPrice(price);
        restaurant.setOpenTime(openTime);

        this.restaurantRepository.save(restaurant);
    }
}
