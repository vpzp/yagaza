package yagaza.com.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
    public String create(@Valid UserCreateForm userCreateForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "signup_form";
        }
        //TODO 패스워드 불일치, 중복된 ID예외처리 작성하고 테스트코드 실행해보기
        if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())){
            bindingResult.rejectValue("password2", "passwordIncorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
        }
        try{
            userService.create(userCreateForm.getId(), userCreateForm.getName(),userCreateForm.getUsername(),
                    userCreateForm.getPassword1(), userCreateForm.getEmail(), userCreateForm.getPhoneNumber());
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자ID입니다");
            return "signup_form";
        }catch (Exception e){
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }
        return "redirect:/";
    }

    //로그인 html파일 return해줘야함
    @GetMapping(value = "/login")
    public String login(){
        return "login_form";
    }
}
