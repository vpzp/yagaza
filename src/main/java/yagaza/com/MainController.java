package yagaza.com;

import jakarta.persistence.criteria.Order;
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

    @GetMapping("/")
    public String root() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main(OrderCreateForm orderCreateForm){
        return "main_form";
    }

}
