package yagaza.com.order;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import yagaza.com.Tourism.Tourism;
import yagaza.com.Tourism.TourismService;
import yagaza.com.admin.ListUtil;
import yagaza.com.hotel.Hotel;
import yagaza.com.hotel.HotelService;
import yagaza.com.restaurant.Restaurant;
import yagaza.com.survey.Survey;
import yagaza.com.survey.SurveyCreateForm;
import yagaza.com.survey.SurveyService;
import yagaza.com.user.UserService;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/order")
public class OrderController {
    private final HotelService hotelService;
    private final OrderService orderService;
    private final UserService userService;
    private final SurveyService surveyService;
    private final TourismService tourismService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/hotel")
    public String orderHotel(Model model, Principal principal){
        model.addAttribute("hotel", hotelService.getHotelList());
        model.addAttribute("siteOrder", orderService.getOrder(userService.getUser(principal.getName())));

        return "choice_hotel_form";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/hotelRoom/{id}")
    public String orderHotelRoom(Model model, Principal principal, @PathVariable("id") Long id){
        Hotel hotel = hotelService.getHotel(id);
        model.addAttribute("hotel", hotel);
        model.addAttribute("siteOrder", orderService.getOrder(userService.getUser(principal.getName())));

        return "choice_hotel_room_form";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/main/{id}")
    public String order(Model model, Principal principal, SurveyCreateForm surveyCreateForm, @PathVariable("id") Long id){
        SiteOrder siteOrder = orderService.getOrder(userService.getUser(principal.getName()));
        Survey survey =surveyService.create(surveyCreateForm.getTourismType(), surveyCreateForm.getTourismCount(), surveyCreateForm.getRestaurantType(),
                surveyCreateForm.getHotelType(),surveyCreateForm.isHotelChange(), siteOrder);

        orderService.setSurvey(siteOrder, survey);
        List<Restaurant>[] restaurantList = surveyService.getRestaurant(survey, siteOrder.getTravel());
        List<Hotel> hotelList = surveyService.getHotel(survey, siteOrder.getTravel());
        List<Tourism> tourismList = surveyService.getTourism(survey, siteOrder.getTravel());
        List<Tourism> tourismAllList = tourismService.getToursimList(siteOrder.getTravel());
        List<Hotel> hotels = hotelService.getHotelType(survey.getHotelType(), siteOrder.getTravel());
        hotels.removeIf(hotel -> hotelService.getHotelPrice(survey.getSiteOrder().getProd(), hotel) == null);

        List<Hotel> hotelListTop10ByPrice = surveyService.getHotelTop10ByPrice(hotels, (int) (siteOrder.getCash() / (siteOrder.getDate() - 1) *0.16), siteOrder.getProd());

        int oneDayCash = siteOrder.getCash() / (siteOrder.getDate() - 1) / siteOrder.getProd();
        List<Restaurant>[] restaurantTop15ByPrice = surveyService.getRestaurantTop15ByPrice((int) (oneDayCash *0.42), false, siteOrder.getTravel());

        orderService.setOrderItem(siteOrder, restaurantList, tourismList, hotelList);

        model.addAttribute("survey" , survey);
        model.addAttribute("siteOrder", siteOrder);
        model.addAttribute("restaurantList" ,restaurantList);
        model.addAttribute("hotelList", hotelList);
        model.addAttribute("tourismList", tourismList);
        model.addAttribute("tourismAllList", tourismAllList);
        model.addAttribute("hotelListTop10ByPrice", hotelListTop10ByPrice);
        model.addAttribute("restaurantTop15ByPrice", restaurantTop15ByPrice);
        model.addAttribute("hotelService", hotelService);
        model.addAttribute("orderService", orderService);
        model.addAttribute("surveyService", surveyService);

        return "order_form";
    }

//  TODO 예약내역 html에서 이 링크 복사해주기
    @GetMapping("/main/{id}")
    public String orderModify(Model model, @PathVariable("id") Long id) throws IOException {
        SiteOrder siteOrder = orderService.getOrder(id);
        Survey survey = siteOrder.getSurvey();

        List<Restaurant>[] restaurantList = ListUtil.convertListToArray(siteOrder.getRestaurantList());
        List<Hotel> hotelList = siteOrder.getHotel();
        List<Tourism> tourismList = siteOrder.getTourism();

        List<Tourism> tourismAllList = tourismService.getToursimList(siteOrder.getTravel());
        List<Hotel> hotels = hotelService.getHotelType(survey.getHotelType(), siteOrder.getTravel());
        hotels.removeIf(hotel -> hotelService.getHotelPrice(survey.getSiteOrder().getProd(), hotel) == null);

        List<Hotel> hotelListTop10ByPrice = surveyService.getHotelTop10ByPrice(hotels, (int) (siteOrder.getCash() / (siteOrder.getDate() - 1) *0.16), siteOrder.getProd());

        int oneDayCash = siteOrder.getCash() / (siteOrder.getDate() - 1) / siteOrder.getProd();
        List<Restaurant>[] restaurantTop15ByPrice = surveyService.getRestaurantTop15ByPrice((int) (oneDayCash *0.42), false, siteOrder.getTravel());

        model.addAttribute("survey" , survey);
        model.addAttribute("siteOrder", siteOrder);
        model.addAttribute("restaurantList" ,restaurantList);
        model.addAttribute("hotelList", hotelList);
        model.addAttribute("tourismList", tourismList);
        model.addAttribute("tourismAllList", tourismAllList);
        model.addAttribute("hotelListTop10ByPrice", hotelListTop10ByPrice);
        model.addAttribute("restaurantTop15ByPrice", restaurantTop15ByPrice);
        model.addAttribute("hotelService", hotelService);
        model.addAttribute("orderService", orderService);
        model.addAttribute("surveyService", surveyService);

        return "order_reserve_form";
    }
}
