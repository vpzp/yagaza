package yagaza.com;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yagaza.com.hotel.Hotel;
import yagaza.com.hotel.HotelService;
import yagaza.com.order.OrderRepository;
import yagaza.com.order.OrderService;
import yagaza.com.order.SiteOrder;
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

    @Test
    public void createOrderTest(){
//        int cash = 700000;
//        int prod = 2;
//        String date = "3";
//        String car = "없음";
//        String travel = "부산";
//        SiteUser siteUser = userService.getUser("관리자");

        int cash = 1100000;
        int prod = 3;
        String date = "3";
        String car = "없음";
        String travel = "부산";
        SiteUser siteUser = userService.getUser("관리자");

        orderService.create(cash, prod, date, car, travel, siteUser);
    }

    @Test
    public void createSurveyTest(){
//        String restaurantType = "한식";
//        List<String> openTime = new ArrayList<>(Arrays.asList("점심, 저녁"));
//        String hotelType = "모텔";
//        List<String> hotelKeyword = new ArrayList<>(Arrays.asList("깔끔한"));
//        int hotelImportance = 2;
//        int restaurantImportance = 2;
//        boolean isHotelChange = false;
//        Optional<SiteOrder> siteOrder = orderRepository.findById(62L);

        String restaurantType = "일식";
        List<String> openTime = new ArrayList<>(Arrays.asList("점심, 저녁"));
        String hotelType = "호텔/리조트";
        List<String> hotelKeyword = new ArrayList<>(Arrays.asList("친절한"));
        int hotelImportance = 2;
        int restaurantImportance = 2;
        boolean isHotelChange = true;
        Optional<SiteOrder> siteOrder = orderRepository.findById(63L);

        surveyService.create(restaurantType, openTime, hotelType, hotelKeyword, hotelImportance,
                restaurantImportance, isHotelChange, siteOrder.get());
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
            System.out.println("호텔 키워드는 = "+ Arrays.toString(hotel.getKeyword().toArray()));
            System.out.println();
        }

        date = 1;
        for(Hotel hotel : hotelList2){
            System.out.println(date++ +" 일차--------------------------");
            System.out.println("호텔 이름은 = " + hotel.getHotelName());
            System.out.println("호텔 가격은 = " + hotelService.getHotelPrice(survey2.getSiteOrder().getProd(), hotel));
            System.out.println("호텔 종류는 = " + hotel.getType());
            System.out.println("호텔 키워드는 = "+ Arrays.toString(hotel.getKeyword().toArray()));
            System.out.println();
        }


    }
}
