package yagaza.com.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    //회원가입 html파일 return해줘야함
    @GetMapping(value = "/signup")
    public String create(UserCreateForm userCreateForm){
        return "signup_form";
    }
    
    @PostMapping(value = "/signup")
    public String create(){
        // TODO: 2023-11-21 오류선언 해줘야함
        return "redirect:/";
    }

    //로그인 html파일 return해줘야함
    @GetMapping(value = "/login")
    public String login(){
        return "login_form";
    }
}
