package yagaza.com.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yagaza.com.user.SiteUser;
import yagaza.com.user.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public void create(String cash, String prod, String date, String car, String travel, SiteUser user){
        SiteOrder siteOrder = new SiteOrder();
        siteOrder.setCash(cash);
        siteOrder.setCar(car);
        siteOrder.setProd(prod);
        siteOrder.setDate(date);
        siteOrder.setTravel(travel);
        siteOrder.setSiteUser(user);

        this.orderRepository.save(siteOrder);
        user.setSiteOrder(siteOrder.getSiteUser().getSiteOrder());
        this.userRepository.save(user);
    }

    public SiteOrder getOrder(SiteUser siteUser) {
        SiteOrder siteOrder = orderRepository.findTopBySiteUserIdOrderByIdDesc(siteUser.getId());
        return siteOrder;
    }
}
