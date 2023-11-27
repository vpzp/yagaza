package yagaza.com.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class UserControllerTest {
    @Autowired
    private UserService userService;
    @Test
    void 회원가입() {
        SiteUser user = this.userService.create("admin", "관리자", "관리자", "q1w2e3r4",
                "admin@naver.com", "01012345678");
        assertThat(user.getId().equals("admin"));
        assertThat(user.getName().equals("관리자"));
        assertThat(user.getUsername().equals("관리자"));
        assertThat(user.getPassword().equals("q1w2e3r4"));
        assertThat(user.getEmail().equals("admin@naver.com"));
        assertThat(user.getPhoneNumber().equals("01012345678"));

    }

    @Test
    void 중복회원_예외() throws Exception {
        SiteUser user1 = new SiteUser();
        user1.setId("test");

        SiteUser user2 = new SiteUser();
        user2.setId("test");

//        userController.create()

    }

    @Test
    void 패스워드_불일치() throws Exception{
        UserCreateForm userCreateForm = new UserCreateForm();
        userCreateForm.setPassword1("0000");
        userCreateForm.setPassword2("1111");

//        userController.create(userCreateForm, )

    }
}