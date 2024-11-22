package yagaza.com.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public void create(String name, String type, String content,  List<String> img, int[] price,
                       List<String> openTime, String region){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setType(type);
        restaurant.setContent(content);
        restaurant.setImg(img);
        restaurant.setPrice(price);
        restaurant.setOpenTime(openTime);
        restaurant.setRegion(region);

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

    public Page<Restaurant> getRestaurantList(int page, String keyword){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

        return restaurantRepository.findAllByNameContaining(keyword, pageable);
    }

    public List<Restaurant> getRestaurantListByType(String type){
        return this.restaurantRepository.findByType(type);
    }

    public void setMap(Restaurant restaurant, Double x, Double y) {
        restaurant.setMapX(x);
        restaurant.setMapY(y);

        restaurantRepository.save(restaurant);
    }
}
