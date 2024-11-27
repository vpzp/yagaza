package yagaza.com;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController {

    @GetMapping("/denied")
    public String accessDenied() {
        return "denied_form";
    }
}
