package yagaza.com.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import yagaza.com.hotel.HotelRoomService;
import yagaza.com.hotel.HotelService;
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

    @GetMapping("/hotel")
    public String orderHotel(Model model, Principal principal){
        model.addAttribute("hotel", hotelService.getHotelList());
        model.addAttribute("siteOrder", orderService.getOrder(userService.getUser(principal.getName())));
        return "choice_hotel_form";
    }
}
