package yagaza.com.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yagaza.com.admin.requestHotel.RequestHotelForm;
import yagaza.com.admin.requestHotel.RequestHotelService;
import yagaza.com.admin.requestRestaurant.RequestRestaurantForm;
import yagaza.com.admin.requestRestaurant.RequestRestaurantService;
import yagaza.com.hotel.Hotel;
import yagaza.com.hotel.HotelService;
import yagaza.com.restaurant.Restaurant;
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

    @GetMapping("/hotellist")
    public String getHotelList(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "keyword", defaultValue = "") String keyword){
        Page<Hotel> hotelList = hotelService.getPagingHotelList(page, keyword);
        model.addAttribute("paging", hotelList);


        return "/admin/hotelList";
    }
    @GetMapping("/hotellist/create")
    public String createHotelForm(RequestHotelForm requestHotelForm){
        return "/admin/hotelForm";
    }

    @PostMapping("/hotellist/create")
    public String createHotel(@Valid RequestHotelForm requestHotelForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/admin/hotelForm";
        }
        requestHotelService.create(requestHotelForm.getHotelName(), requestHotelForm.getRegion(), requestHotelForm.getPriceOnePerson(),
                requestHotelForm.getPriceTwoPerson(), requestHotelForm.getPriceThreePerson(), requestHotelForm.getPriceFourPerson(),
                requestHotelForm.getPriceFivePerson(), requestHotelForm.getImg(), requestHotelForm.getType(),
                requestHotelForm.getCheckInTime());

//        TODO 주소값 설정
        return "redirect:/";
    }

    @GetMapping("/restaurantList")
    public String getRestaurantList(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "keyword", defaultValue = "") String keyword){
        Page<Restaurant> restaurantList = restaurantService.getRestaurantList(page, keyword);
        model.addAttribute("paging", restaurantList);

        return "/admin/restaurantList";
    }

    @GetMapping("/restaurantList/create")
    public String createRestaurantForm(RequestRestaurantForm requestRestaurantForm){
        return "/admin/restaurantForm";
    }

    @PostMapping("restaurantList/create")
    public String createRestaurant(@Valid RequestRestaurantForm requestRestaurantForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/admin/restaurantForm";
        }
        requestRestaurantService.create(requestRestaurantForm.getName(), requestRestaurantForm.getRegion(),
                requestRestaurantForm.getContent(), requestRestaurantForm.getPrice(), requestRestaurantForm.getOpenTime(),
                requestRestaurantForm.getType(), requestRestaurantForm.getImg());

        //        TODO 주소값 설정
        return "redirect:/";
    }
}
