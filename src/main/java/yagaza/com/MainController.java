package yagaza.com;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yagaza.com.order.OrderCreateForm;
import yagaza.com.order.OrderService;
import yagaza.com.user.SiteUser;
import yagaza.com.user.UserService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/")
    public String root() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main(){
        return "main_form";
    }

    @GetMapping("/order")
    public String survey(){
        return"choice_hotel_form";
    }


//TODO 메인화면에 사용자 정보 값 추가하기


    //메인페이지에서 입력한값 저장하고 다음페이지로 넘기기
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/main")
    public String main(OrderCreateForm orderCreateForm, Principal principal)  {
        SiteUser siteUser =this.userService.getUser(principal.getName());
        /// TODO: 2024-05-13 메인화면 cash랑 인원수 int형으로 받아와야함 주석 오류있음
//        this.orderService.create(orderCreateForm.getCash(), orderCreateForm.getProd(), orderCreateForm.getDate(),
//                orderCreateForm.getCar(), "부산", siteUser);

        return "survey";
    }
}
