package yagaza.com.admin.requestRestaurant;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import yagaza.com.restaurant.Restaurant;
import yagaza.com.restaurant.RestaurantService;
import yagaza.com.user.SiteUser;
import yagaza.com.user.UserRoleService;
import yagaza.com.user.UserService;

import java.security.Principal;
import java.util.List;

@RequestMapping("/admin")
@Controller
@RequiredArgsConstructor
public class RequestRestaurantController {
    private final RestaurantService restaurantService;
    private final RequestRestaurantService requestRestaurantService;
    private final UserService userService;
    private final UserRoleService userRoleService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/restaurantList")
    public String getRestaurantList(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "keyword", defaultValue = "") String keyword){
        Page<Restaurant> restaurantList = restaurantService.getRestaurantList(page, keyword);
        model.addAttribute("paging", restaurantList);

        return "/admin/restaurantList";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/restaurant/{id}")
    public String getRestaurant(@PathVariable("id") Long id, Model model){
        Restaurant restaurant = restaurantService.getRestaurant(id);
        model.addAttribute("restaurant", restaurant);

        return "/admin/restaurant_detail";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PARTNER')")
    @GetMapping("/requestRestaurantList")
    public String getRequestRestaurantList(Model model,@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "keyword", defaultValue = "") String keyword){
        boolean isAdmin = userRoleService.checkAdmin();
        if (!isAdmin){
            return "redirect:/myRequestHotel";
        }

        Page<RequestRestaurant> requestRestaurantList = requestRestaurantService.getPagingRestaurant(page, keyword);
        model.addAttribute("paging", requestRestaurantList);

        return "/admin/restaurant_request_list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/requestRestaurantList/update")
    public String updateRestaurantStatus(Long id, String status){
        RequestRestaurant requestRestaurant = requestRestaurantService.updateStatus(id, status);
        if (status.equals("대기중")){
            return "redirect:/admin/myRequestRestaurant";
        }

        int[] arrPrice = new int[requestRestaurant.getPrice().size()];
        for (int i = 0; i < arrPrice.length; i++) {
            arrPrice[i] = requestRestaurant.getPrice().get(i);
        }

        Restaurant restaurant = restaurantService.create(requestRestaurant.getName(), requestRestaurant.getType(),
                requestRestaurant.getContent(), requestRestaurant.getImg(), arrPrice,
                requestRestaurant.getOpenTime(), requestRestaurant.getRegion());

        restaurantService.setMap(restaurant);

        return "redirect:/admin/requestRestaurantList";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PARTNER')")
    @GetMapping("/requestRestaurantList/{id}")
    public String getRequestRestaurant(@PathVariable("id") Long id, Model model){
        RequestRestaurant restaurant = requestRestaurantService.getRestaurant(id);
        model.addAttribute("restaurant", restaurant);

        return "/admin/restaurant_request_detail";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PARTNER')")
    @GetMapping("/restaurantList/create")
    public String createRestaurantForm(RequestRestaurantForm requestRestaurantForm){
        return "/admin/restaurantForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PARTNER')")
    @PostMapping("/restaurantList/create")
    public String createRestaurant(@Valid RequestRestaurantForm requestRestaurantForm, BindingResult bindingResult,
                                   Principal principal){
        if (bindingResult.hasErrors()){
            return "/admin/restaurantForm";
        }

        List<Integer> price = requestRestaurantForm.getPrice();
        for (int i = 0; i < price.size(); i++) {
            if (price.get(i) == null){
                price.set(i, 0);
                continue;
            }
            // 숫자가 아닌 경우 에러 추가
            try {
                if (price.get(i) < 0) { // 음수인 경우 처리
                    bindingResult.rejectValue("price", "error.price", "가격은 0 이상의 숫자만 입력 가능합니다.");
                }
            } catch (NumberFormatException e) {
                bindingResult.rejectValue("price", "error.price", "가격은 숫자만 입력 가능합니다.");
                return "/admin/restaurantForm"; // 에러가 있으면 폼 다시 렌더링
            }
        }

        SiteUser user = userService.getUser(principal.getName());
        requestRestaurantService.create(requestRestaurantForm.getName(), requestRestaurantForm.getRegion(),
                requestRestaurantForm.getContent(), requestRestaurantForm.getPrice(), requestRestaurantForm.getOpenTime(),
                requestRestaurantForm.getType(), requestRestaurantForm.getImg(), user.getId());

        return "redirect:/admin/restaurantList";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/restaurantList/delete/{id}")
    public String deleteRestaurant(@PathVariable("id")Long id){
        restaurantService.deleteRestaurant(restaurantService.getRestaurant(id));

        return "redirect:/admin/hotelList";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PARTNER')")
    @GetMapping("/myRequestRestaurant")
    public String myRequestRestaurant(Model model,@RequestParam(value = "page", defaultValue = "0") int page,
                                     Principal principal){
        SiteUser user = userService.getUser(principal.getName());
        Page<RequestRestaurant> restaurant = requestRestaurantService.getPagingMyRestaurant(page, user.getId());
        model.addAttribute("paging", restaurant);

        return "/admin/my_request_restaurant";
    }
}
