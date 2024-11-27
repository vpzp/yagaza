package yagaza.com.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yagaza.com.Tourism.Tourism;
import yagaza.com.hotel.Hotel;
import yagaza.com.hotel.HotelService;
import yagaza.com.restaurant.Restaurant;
import yagaza.com.survey.Survey;
import yagaza.com.user.SiteUser;
import yagaza.com.user.UserRepository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final HotelService hotelService;

    public SiteOrder create(int cash, int prod, int date, String car, String travel, SiteUser user){
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        SiteOrder siteOrder = new SiteOrder();
        siteOrder.setCash(cash);
        siteOrder.setCar(car);
        siteOrder.setProd(prod);
        siteOrder.setDate(date);
        siteOrder.setTravel(travel);
        siteOrder.setSiteUser(user);
        siteOrder.setCreateDateTime(zonedDateTime.toLocalDateTime());

        this.orderRepository.save(siteOrder);

        user.setSiteOrder(siteOrder.getSiteUser().getSiteOrder());
        this.userRepository.save(user);

        return siteOrder;
    }

    public SiteOrder getOrder(SiteUser siteUser) {
        SiteOrder siteOrder = orderRepository.findTopByOrderByIdDesc();
        return siteOrder;
    }

    public void setSurvey(SiteOrder siteOrder, Survey survey){
        siteOrder.setSurvey(survey);
        this.orderRepository.save(siteOrder);
    }

    public int getAllTourismPrice(List<Tourism> tourismList){
        int sum = 0;
        for (Tourism tourism : tourismList){
            sum += tourism.getPrice();
        }
        return sum;
    }

    public int getAllHotelPrice(List<Hotel> hotelList, SiteOrder siteOrder){
        int sum = 0;
        for (Hotel hotel : hotelList){
            sum += hotelService.getHotelPrice(siteOrder.getProd(), hotel);
        }
        return sum;
    }

    public int getAllRestaurantPrice(List<Restaurant>[] restaurantList){
        int sum = 0;
        for (int i = 0 ; i < restaurantList.length ; i++){
            for (Restaurant restaurant : restaurantList[i]){
                sum += restaurant.getPrice()[i];
            }
        }
        return sum;
    }

    public List<SiteOrder> findBySiteUserRealIdOrderByIdDesc(Long realId){
        return this.orderRepository.findBySiteUserRealIdOrderByIdDesc(realId);
    }
}
