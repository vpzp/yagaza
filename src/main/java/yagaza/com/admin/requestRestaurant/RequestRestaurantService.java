package yagaza.com.admin.requestRestaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestRestaurantService {
    private final RequestRestaurantRepository requestRestaurantRepository;

    public void create(String name, String region, String content, int[] price,
                       List<String> openTime, String type, List<String> img){
        RequestRestaurant requestRestaurant = new RequestRestaurant();
        requestRestaurant.setName(name)
                .setRegion(region)
                .setContent(content)
                .setPrice(price)
                .setOpenTime(openTime)
                .setType(type)
                .setImg(img);

        requestRestaurantRepository.save(requestRestaurant);
    }
}
