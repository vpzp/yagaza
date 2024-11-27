package yagaza.com.admin.requestRestaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import yagaza.com.DataNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestRestaurantService {
    private final RequestRestaurantRepository requestRestaurantRepository;

    public void create(String name, String region, String content, List<Integer> price,
                       List<String> openTime, String type, List<String> img, String userId){
        RequestRestaurant requestRestaurant = new RequestRestaurant();

        requestRestaurant.setName(name)
                .setRegion(region)
                .setContent(content)
                .setPrice(price)
                .setOpenTime(openTime)
                .setType(type)
                .setImg(img)
                .setUserId(userId)
                .setStatus("대기중");

        requestRestaurantRepository.save(requestRestaurant);
    }

    public Page<RequestRestaurant> getPagingRestaurant(int page, String keyword) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

        return requestRestaurantRepository.findAllByNameContaining(keyword, pageable);
    }

    public Page<RequestRestaurant> getPagingMyRestaurant(int page, String userId) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

        return requestRestaurantRepository.findAllByUserId(userId, pageable);
    }

    public RequestRestaurant getRestaurant(Long id) {
        Optional<RequestRestaurant> requestRestaurantOptional = requestRestaurantRepository.findById(id);
        if (requestRestaurantOptional.isPresent()){
            return requestRestaurantOptional.get();
        }else {
            throw new DataNotFoundException("requestRestaurant 객체 없습니다");
        }
    }

    public RequestRestaurant updateStatus(Long id, String status) {
        RequestRestaurant restaurant = getRestaurant(id);
        restaurant.setStatus(status);

        requestRestaurantRepository.save(restaurant);

        return restaurant;
    }
}
