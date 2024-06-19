package yagaza.com.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<SiteOrder, Long> {
    List<SiteOrder> findBySiteUserId(String username);

    Optional<SiteOrder> findById(Long id);

    SiteOrder findTopByOrderByIdDesc();

    List<SiteOrder> findBySiteUserRealIdOrderByIdDesc(Long realId);
}
