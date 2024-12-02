package yagaza.com.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import yagaza.com.DataNotFoundException;
import yagaza.com.Geocoding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final Geocoding geocoding;

    public Restaurant create(String name, String type, String content,  List<String> img, int[] price,
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

        return restaurant;
    }

    public List<Restaurant> getRestaurantList(String region){
        return this.restaurantRepository.findAllByRegion(region);
    }

    public List<Restaurant> getRestaurantList(){
        return this.restaurantRepository.findAll();
    }

    public Restaurant getRestaurant(Long id){
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        if (restaurantOptional.isPresent()){
            return restaurantOptional.get();
        }else {
            throw new DataNotFoundException("restaurant 객체 없습니다");
        }
    }

    public void deleteRestaurant(Restaurant restaurant){
        restaurantRepository.delete(restaurant);
    }

    public List<Restaurant> getRestaurantListByOpenTime(String openTime, String region){
        List<Restaurant> restaurantList = getRestaurantList(region);
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

    public void setAllMap(){
        List<Restaurant> restaurantList = getRestaurantList();
        for (Restaurant restaurant : restaurantList) {
            setMap(restaurant);
        }
    }

    public void setMap(Restaurant restaurant) {

        Map<String, Double> map = geocoding.fecthMap(restaurant.getName());
        restaurant.setMapX(map.get("x"));
        restaurant.setMapY(map.get("y"));

        restaurantRepository.save(restaurant);
    }
}
