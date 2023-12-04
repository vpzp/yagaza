package yagaza.com;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yagaza.com.user.SiteUser;
import yagaza.com.user.UserService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;

    @GetMapping("/")
    public String root() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main(){
        return "main";
    }

//TODO PostMapping으로 메인화면에 사용자 정보 값 추가하기

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/main")
    public String main(Principal principal, Model model) {
        SiteUser siteUser = this.userService.getUser(principal.getName());
        model.addAttribute("username", siteUser.getUsername());
        return "main";
    }
}
