package yagaza.com.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yagaza.com.admin.requestHotel.RequestHotelService;
import yagaza.com.admin.requestRestaurant.RequestRestaurantService;
import yagaza.com.hotel.HotelService;
import yagaza.com.restaurant.RestaurantService;
import yagaza.com.user.SiteUser;
import yagaza.com.user.UserService;

@RequestMapping("/admin")
@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final HotelService hotelService;
    private final RestaurantService restaurantService;
    private final RequestHotelService requestHotelService;
    private final RequestRestaurantService requestRestaurantService;


    @GetMapping("/userlist")
    public String getUserList(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "keyword", defaultValue = "") String keyword){
        Page<SiteUser> userList = userService.getUserList(page, keyword);
        model.addAttribute("paging", userList);

        return "/admin/userList";
    }

    @PostMapping("/userlist/update")
    public String updateUserAuthority(String id, String authority){
        userService.updateAuthority(id, authority);

        return "redirect:/admin/userlist";

    }

    @GetMapping("/test")
    public String test(){

        return "hotel_detail";
    }

}
