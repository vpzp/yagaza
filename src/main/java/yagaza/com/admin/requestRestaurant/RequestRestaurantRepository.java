package yagaza.com.admin.requestRestaurant;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRestaurantRepository extends JpaRepository<RequestRestaurant, Long> {


}
