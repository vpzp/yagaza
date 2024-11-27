package yagaza.com.admin.requestHotel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import yagaza.com.hotel.Hotel;
import yagaza.com.hotel.HotelService;
import yagaza.com.user.SiteUser;
import yagaza.com.user.UserService;

import java.security.Principal;

@RequestMapping("/admin")
@Controller
@RequiredArgsConstructor
public class RequestHotelController {
    private final HotelService hotelService;
    private final RequestHotelService requestHotelService;
    private final UserService userService;

    @GetMapping("/hotelList")
    public String getHotelList(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "keyword", defaultValue = "") String keyword){
        Page<Hotel> hotelList = hotelService.getPagingHotelList(page, keyword);
        model.addAttribute("paging", hotelList);


        return "/admin/hotelList";
    }

    @GetMapping("/hotelList/{id}")
    public String getHotel(@PathVariable("id") Long id, Model model){
        Hotel hotel = hotelService.getHotel(id);
        model.addAttribute("hotel", hotel);

        return "/admin/hotel_detail";
    }


    @GetMapping("/requestHotelList")
    public String getRequestHotelList(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "keyword", defaultValue = "") String keyword){
        Page<RequestHotel> requestHotelList = requestHotelService.getPagingHotelList(page, keyword);
        model.addAttribute("paging", requestHotelList);

        return "/admin/hotel_request_list";
    }
    @PostMapping("/requestHotelList/update")
    public String updateHotelStatus(Long id, String status){
        RequestHotel requestHotel = requestHotelService.updateStatus(id, status);

        Hotel hotel = hotelService.createHotel(requestHotel.getHotelName(), requestHotel.getCheckInTime(),
                requestHotel.getImg(), requestHotel.getType(), requestHotel.getRegion());

        hotelService.setHotelPrice(hotel, requestHotel.getPriceOnePerson(), requestHotel.getPriceTwoPerson()
        , requestHotel.getPriceThreePerson(), requestHotel.getPriceFourPerson(), requestHotel.getPriceFivePerson());

        hotelService.setMap(hotel);

        return "redirect:/admin/requestHotelList";
    }

    @GetMapping("/requestHotelList/{id}")
    public String getRequestHotel(@PathVariable("id") Long id, Model model) {
        RequestHotel hotel = requestHotelService.getHotel(id);
        model.addAttribute("hotel", hotel);

        return "/admin/hotel_request_detail";
    }
    @GetMapping("/hotelList/create")
    public String createHotelForm(RequestHotelForm requestHotelForm){
        return "/admin/hotelForm";
    }

    @PostMapping("/hotelList/create")
    public String createHotel(@Valid RequestHotelForm requestHotelForm, BindingResult bindingResult, Principal principal){
        if (bindingResult.hasErrors()){
            return "/admin/hotelForm";
        }
        SiteUser user = userService.getUser(principal.getName());
        requestHotelService.create(requestHotelForm.getHotelName(), requestHotelForm.getRegion(), requestHotelForm.getPriceOnePerson(),
                requestHotelForm.getPriceTwoPerson(), requestHotelForm.getPriceThreePerson(), requestHotelForm.getPriceFourPerson(),
                requestHotelForm.getPriceFivePerson(), requestHotelForm.getImg(), requestHotelForm.getType(),
                requestHotelForm.getCheckInTime(), user.getId());

//        TODO 주소값 설정
        return "redirect:/admin/hotelList";
    }

    @GetMapping("/myRequestHotel")
    public String myRequestHotel(Model model,@RequestParam(value = "page", defaultValue = "0") int page,
                                 Principal principal){
        SiteUser user = userService.getUser(principal.getName());
        Page<RequestHotel> hotelList = requestHotelService.getPagingMyHotelList(page, user.getId());
        model.addAttribute("paging", hotelList);

        return "/admin/my_request_hotel";
    }

}
