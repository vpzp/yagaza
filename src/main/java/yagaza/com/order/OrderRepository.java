package yagaza.com.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<SiteOrder, Long> {
    List<SiteOrder> findBySiteUserId(String username);

    SiteOrder findTopBySiteUserIdOrderByIdDesc(String username);
}
