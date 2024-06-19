package yagaza.com.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import yagaza.com.order.OrderService;
import yagaza.com.order.SiteOrder;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final OrderService orderService;

    @GetMapping(value = "/signup")
    public String create(UserCreateForm userCreateForm){
        return "signup_form";
    }
    
    @PostMapping(value = "/signup")
    public String create(@Valid UserCreateForm userCreateForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "signup_form";
        }

        if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())){
            bindingResult.rejectValue("password2", "passwordIncorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }
        try{
            userService.create(userCreateForm.getId(), userCreateForm.getName(),userCreateForm.getUsername(),
                    userCreateForm.getPassword1(), userCreateForm.getEmail(), userCreateForm.getPhoneNumber());
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

        return "redirect:/";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/passwordChange")
    public String modify(UserCreateForm userCreateForm){
        return "password_change";
    }

    //TODO 비밀번호 변경 로직 구상하기 html파일 받으면 시작
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/passwordChange")
    public String modify(@Valid UserModifyForm userModifyForm, BindingResult bindingResult, Principal principal)  {
        if(bindingResult.hasErrors()){
            return "password_change";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());
        if(passwordEncoder.matches(userModifyForm.getPassword(), siteUser.getPassword())){
            bindingResult.rejectValue("password", "passwordSame",
                    "기존의 비밀번호를 입력했습니다.");
            return "password_change";
        } else if (!userModifyForm.getNewPassword1().equals(userModifyForm.getNewPassword2())) {
            bindingResult.rejectValue("NewPassword2", "passwordIncorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "password_change";
        }
        try{

        }catch (Exception e){
            e.printStackTrace();
            bindingResult.reject("modifyFailed", e.getMessage());
            return "password_change";
        }
        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete")
    public String delete(){
        return "unregister";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/mypage")
    public String myPage(){
        return "mypage";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/useList")
    public String useList(Principal principal, Model model){
        SiteUser siteUser = this.userService.getUser(principal.getName());
        List<SiteOrder> siteOrderList =orderService.findBySiteUserRealIdOrderByIdDesc(siteUser.getRealId());
        model.addAttribute("siteOrderList", siteOrderList);
        return "UseList";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "login_form";
    }
}
