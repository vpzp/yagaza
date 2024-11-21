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
import yagaza.com.order.SiteOrder;
import yagaza.com.restaurant.Restaurant;
import yagaza.com.restaurant.RestaurantService;
import yagaza.com.survey.Survey;
import yagaza.com.survey.SurveyRepository;
import yagaza.com.survey.SurveyService;
import yagaza.com.user.SiteUser;
import yagaza.com.user.UserService;

import java.util.*;

@SpringBootTest
public class orderTest {
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
    public void createOrderTest(){
//        int cash = 700000;
//        int prod = 2;
//        String date = "3";
//        String car = "없음";
//        String travel = "부산";
//        SiteUser siteUser = userService.getUser("관리자");

//        int cash = 1100000;
//        int prod = 3;
//        String date = "3";
//        String car = "없음";
//        String travel = "부산";
//        SiteUser siteUser = userService.getUser("관리자");

        int cash = 300000;
        int prod = 2;
        int date = 2;
        String car = "없음";
        String travel = "부산";
        SiteUser siteUser = userService.getUser("관리자");

        orderService.create(cash, prod, date, car, travel, siteUser);
    }

    @Test
    public void createSurveyTest(){

        String tourismType = "활동";
        int tourismDayCount = 1;
        String restaurantType = "기타 세계 음식";
        List<String> openTime = new ArrayList<>(Arrays.asList("점심, 저녁"));
        String hotelType = "모텔";
        List<String> hotelKeyword = new ArrayList<>(Arrays.asList("깔끔한"));
        int hotelImportance = 2;
        int restaurantImportance = 2;
        boolean isHotelChange = true;
        Optional<SiteOrder> siteOrder = orderRepository.findById(64L);

        surveyService.create(tourismType, tourismDayCount, restaurantType, openTime, hotelType,
                hotelImportance, restaurantImportance, isHotelChange, siteOrder.get());
    }

    @Test
    public void getRestaurantTest(){
        Survey survey = surveyRepository.findAll().get(0);
        Survey survey2 = surveyRepository.findAll().get(1);

        List<Restaurant>[] restaurantList = surveyService.getRestaurant(survey);
        List<Restaurant>[] restaurantList2 = surveyService.getRestaurant(survey2);
        for (int i = 0 ; i < restaurantList[0].size(); i++){
            System.out.println("점심");
            System.out.println("레스토랑 이름은 : " + restaurantList[0].get(i).getName());
            System.out.println("레스토랑 가격은 : " + restaurantList[0].get(i).getPrice()[0]);
            System.out.println("레스토랑 오픈 시간은 : " + restaurantList[0].get(i).getOpenTime().toString());

            System.out.println("저녁");
            System.out.println("레스토랑 이름은 : " + restaurantList[1].get(i).getName());
            System.out.println("레스토랑 가격은 : " + restaurantList[1].get(i).getPrice()[1]);
            System.out.println("레스토랑 오픈 시간은 : " + restaurantList[1].get(i).getOpenTime().toString());
            System.out.println("하루식사 가격은 : " + (restaurantList[0].get(i).getPrice()[0] + restaurantList[1].get(i).getPrice()[1]));

            System.out.println();
        }
    }
    @Test
    public void getHotelTest(){
        Survey survey = surveyRepository.findAll().get(0);
        Survey survey2 = surveyRepository.findAll().get(1);

        List<Hotel> hotelList = surveyService.getHotel(survey);
        List<Hotel> hotelList2 = surveyService.getHotel(survey2);
        int date = 1;
        for(Hotel hotel : hotelList){
            System.out.println(date++ +" 일차--------------------------");
            System.out.println("호텔 이름은 = " + hotel.getHotelName());
            System.out.println("호텔 가격은 = " + hotelService.getHotelPrice(survey.getSiteOrder().getProd(), hotel));
            System.out.println("호텔 종류는 = " + hotel.getType());
            System.out.println();
        }

    }
    @Test
    public void getTourismTest(){
        Survey survey = surveyRepository.findAll().get(0);
        Survey survey2 = surveyRepository.findAll().get(1);

        List<Tourism> tourismList = surveyService.getTourism(survey);
        List<Tourism> tourismList2 = surveyService.getTourism(survey2);

        int sum = 0;
        int date = 1;
        for (Tourism tourism : tourismList){
            System.out.println("관광지 이름은 : " + tourism.getName());
            System.out.println("관광지 종류는 : " + tourism.getType());
            System.out.println("관광지 가격은 : " + tourism.getPrice());
            System.out.println();
            sum += tourism.getPrice();
        }
        System.out.println("관광지 총 가격은 : " + sum);

    }
}
