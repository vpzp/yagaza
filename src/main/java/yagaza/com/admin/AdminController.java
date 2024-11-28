package yagaza.com.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;

    @GetMapping("/userList")
    public String getUserList(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "keyword", defaultValue = "") String keyword){
        Page<SiteUser> userList = userService.getUserList(page, keyword);
        model.addAttribute("paging", userList);

        return "admin/userList";
    }

    @PostMapping("/userList/update")
    public String updateUserAuthority(String id, String authority){
        userService.updateAuthority(id, authority);

        return "redirect:/admin/userList";

    }

}
