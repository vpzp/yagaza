package yagaza.com;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yagaza.com.order.OrderService;
import yagaza.com.user.SiteUser;
import yagaza.com.user.UserService;

@SpringBootTest
public class orderTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @Test
    public void createTest(){
        int cash = 700000;
        int prod = 2;
        String date = "3";
        String car = "없음";
        String travel = "부산";
        SiteUser siteUser = userService.getUser("관리자");

        orderService.create(cash, prod, date, car, travel, siteUser);
    }

}
