package yagaza.com;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yagaza.com.Tourism.Tourism;
import yagaza.com.Tourism.TourismService;
import yagaza.com.hotel.Hotel;
import yagaza.com.hotel.HotelService;
import yagaza.com.order.OrderRepository;
import yagaza.com.order.OrderService;
import yagaza.com.restaurant.Restaurant;
import yagaza.com.restaurant.RestaurantService;
import yagaza.com.survey.Survey;
import yagaza.com.survey.SurveyRepository;
import yagaza.com.survey.SurveyService;
import yagaza.com.user.UserService;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class presentTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private SurveyService surveyService;
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private TourismService tourismService;

    @Test
    public void test(){
        Survey survey = surveyRepository.findAll().get(1);
        print(survey);
    }
    @Test
    public void print(Survey survey){
        List<Restaurant>[] restaurantList = surveyService.getRestaurant(survey);
        List<Hotel> hotelList = surveyService.getHotel(survey);
        List<Tourism> tourismList = surveyService.getTourism(survey);

        int index = 0;
        for (int date = 1 ; date <= Integer.parseInt(survey.getSiteOrder().getDate()) ; date++){
            System.out.println(date + "일차 ------------------------------------------");

            //관광지 출력
            for (int j = index * survey.getTourismDayCount() ; j < date * survey.getTourismDayCount() &&
                    date != Integer.parseInt(survey.getSiteOrder().getDate()) ; j++){
                System.out.println("관광지");
                System.out.println("관광지 이름은 : " + tourismList.get(j).getName());
                System.out.println("관광지 종류는 : " + tourismList.get(j).getType());
                System.out.println("관광지 가격은 : " + tourismList.get(j).getPrice());
                System.out.println();
            }

            //점심 출력
            if (date != 1){
                System.out.println("점심");
                System.out.println("레스토랑 이름은 : " + restaurantList[0].get(index - 1).getName());
                System.out.println("레스토랑 소개는 : " + restaurantList[0].get(index - 1).getContent());
                System.out.println("레스토랑 가격은 : " + restaurantList[0].get(index - 1).getPrice()[0]);
                System.out.println("레스토랑 오픈 시간은 : " + restaurantList[0].get(index -1).getOpenTime().toString());
                System.out.println();
            }

            //저녁 출력
            if (date != Integer.parseInt(survey.getSiteOrder().getDate())){
                System.out.println("저녁");
                System.out.println("레스토랑 이름은 : " + restaurantList[1].get(index).getName());
                System.out.println("레스토랑 소개는 : " + restaurantList[1].get(index).getContent());
                System.out.println("레스토랑 가격은 : " + restaurantList[1].get(index).getPrice()[1]);
                System.out.println("레스토랑 오픈 시간은 : " + restaurantList[1].get(index).getOpenTime().toString());
                System.out.println();
            }

            //TODO 야식 출력

            //호텔 출력
            if (date != Integer.parseInt(survey.getSiteOrder().getDate())){
                System.out.println("호텔");
                System.out.println("호텔 이름은 = " + hotelList.get(index).getHotelName());
                System.out.println("호텔 가격은 = " + hotelService.getHotelPrice(survey.getSiteOrder().getProd(), hotelList.get(index)));
                System.out.println("호텔 종류는 = " + hotelList.get(index).getType());
                System.out.println("호텔 키워드는 = "+ Arrays.toString(hotelList.get(index).getKeyword().toArray()));
                System.out.println();
            }
            index++;
        }

    }

}
