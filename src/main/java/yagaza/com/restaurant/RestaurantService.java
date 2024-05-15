package yagaza.com.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Restaurant> getRestaurantList(){
        return this.restaurantRepository.findAll();
    }


    public List<Restaurant> getRestaurantListByOpenTime(String openTime){
        List<Restaurant> restaurantList = getRestaurantList();
        List<Restaurant> restaurants = new ArrayList<>();
        for(Restaurant restaurant : restaurantList){
            if (restaurant.getOpenTime().contains(openTime)){
                restaurants.add(restaurant);
            }
        }
        return restaurants;

    }

    public List<Restaurant> getRestaurantListByType(String type){
        return this.restaurantRepository.findByType(type);
    }
}
