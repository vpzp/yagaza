package yagaza.com.admin.requestRestaurant;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRestaurantRepository extends JpaRepository<RequestRestaurant, Long> {

    Page<RequestRestaurant> findAllByNameContaining(String keyword, Pageable pageable);

    Page<RequestRestaurant> findAllByUserId(String userId, Pageable pageable);

}
