package yagaza.com.survey;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import yagaza.com.order.OrderCreateForm;
import yagaza.com.order.OrderService;
import yagaza.com.order.SiteOrder;
import yagaza.com.user.SiteUser;
import yagaza.com.user.UserService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class SurveyController {
    private final SurveyService surveyService;
    private final UserService userService;
    private final OrderService orderService;

    //메인페이지에서 입력한값 저장하고 다음페이지로 넘기기
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/survey")
    public String main(OrderCreateForm orderCreateForm, Principal principal, SurveyCreateForm surveyCreateForm, Model model)  {
        SiteUser siteUser =this.userService.getUser(principal.getName());
        SiteOrder siteOrder = this.orderService.create(orderCreateForm.getCash(), orderCreateForm.getProd(), orderCreateForm.getDate(),
                orderCreateForm.getCar(), "부산", siteUser);
        model.addAttribute("siteOrder", siteOrder);

        return "survey";
    }
//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/survey")
//    public String main()  {
//        return "survey";
//    }
}
