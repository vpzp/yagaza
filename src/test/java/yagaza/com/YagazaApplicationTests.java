package yagaza.com;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.util.Assert;
import yagaza.com.user.SiteUser;
import yagaza.com.user.UserRepository;
import yagaza.com.user.UserService;

@SpringBootTest
class YagazaApplicationTests {
	@Autowired
	private UserRepository userRepository;
	private UserService userService;

	@Test
	void 회원가입() {
		userService.create("admin", "관리자", "관리자", "q1w2e3r4",
				"admin@naver.com", "01012345678");
//		assertThat(user.getId().equals("admin"));
//		assertThat(user.getName().equals("관리자"));
//		assertThat(user.getUsername().equals("관리자"));
//		assertThat(user.getPassword().equals("q1w2e3r4"));
//		assertThat(user.getEmail().equals("admin@naver.com"));
//		assertThat(user.getPhoneNumber().equals("01012345678"));

	}

}
