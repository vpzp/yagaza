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
import yagaza.com.hotel.Hotel;
import yagaza.com.hotel.HotelRoomService;
import yagaza.com.hotel.HotelService;
import yagaza.com.survey.Survey;
import yagaza.com.survey.SurveyService;
import yagaza.com.user.UserService;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
@RequestMapping("/order")
public class OrderController {
    private final HotelService hotelService;
    private final OrderService orderService;
    private final HotelRoomService hotelRoomService;
    private final UserService userService;
    private final SurveyService surveyService;

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
    //TODO 주소값은 main/{survey.id} 로 설정하기
    @GetMapping("/main")
    public String order(Model model, Principal principal, Survey survey){
        //TODO surveyId값을 넘겨줘야함
        Long id = 4L;
        survey = surveyService.getById(id);
        SiteOrder siteOrder = survey.getSiteOrder();

        model.addAttribute("siteOrder", siteOrder);
        return "order_form";
    }
}
