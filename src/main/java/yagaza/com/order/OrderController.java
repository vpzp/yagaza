package yagaza.com.order;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import yagaza.com.Tourism.Tourism;
import yagaza.com.Tourism.TourismService;
import yagaza.com.hotel.Hotel;
import yagaza.com.hotel.HotelRoomService;
import yagaza.com.hotel.HotelService;
import yagaza.com.restaurant.Restaurant;
import yagaza.com.survey.Survey;
import yagaza.com.survey.SurveyCreateForm;
import yagaza.com.survey.SurveyService;
import yagaza.com.user.UserService;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/order")
public class OrderController {
    private final HotelService hotelService;
    private final OrderService orderService;
    private final HotelRoomService hotelRoomService;
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
        model.addAttribute("hotelRoom",hotelRoomService.getHotelRoomListByHotelId(hotel.getId()));

        return "choice_hotel_room_form";
    }
    @PreAuthorize("isAuthenticated()")
    //TODO 주소값은 main/{siteOrder.id} 로 설정하기
    @PostMapping("/main/{id}")
    public String order(Model model, Principal principal, SurveyCreateForm surveyCreateForm, @PathVariable("id") Long id){
        SiteOrder siteOrder = orderService.getOrder(userService.getUser(principal.getName()));
        //TODO surveyId값을 넘겨줘야함
        Survey survey =surveyService.create(surveyCreateForm.getTourismType(), surveyCreateForm.getTourismCount(), surveyCreateForm.getRestaurantType(),
                surveyCreateForm.getOpenTime(), surveyCreateForm.getHotelType(), surveyCreateForm.getHotelKeyword(), surveyCreateForm.getHotelImportance(),
                surveyCreateForm.getRestaurantImportance(),surveyCreateForm.isHotelChange(), siteOrder);

        List<Restaurant>[] restaurantList = surveyService.getRestaurant(survey);
        List<Hotel> hotelList = surveyService.getHotel(survey);
        List<Tourism> tourismList = surveyService.getTourism(survey);
        List<Tourism> tourismAllList = tourismService.getToursimList();
        List<Hotel> hotels = hotelService.getHotelTypeAndKeyword(survey.getHotelType(), survey.getHotelKeyword());
        hotels.removeIf(hotel -> hotelService.getHotelPrice(survey.getSiteOrder().getProd(), hotel) == null);

        List<Hotel> hotelListTop10ByPrice = surveyService.getHotelTop10ByPrice(hotels, (int) (siteOrder.getCash() / (siteOrder.getDate() - 1) *0.16), siteOrder.getProd());

        int oneDayCash = siteOrder.getCash() / (siteOrder.getDate() - 1) / siteOrder.getProd();
        List<Restaurant>[] restaurantTop15ByPrice = surveyService.getRestaurantTop15ByPrice((int) (oneDayCash *0.42), false);

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
}
