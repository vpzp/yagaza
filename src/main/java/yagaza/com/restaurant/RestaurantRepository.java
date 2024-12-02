package yagaza.com.restaurant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Restaurant findByName(String name);

    @Query("SELECT r FROM Restaurant r WHERE r.type = :type")
    List<Restaurant> findByType(@Param("type") String type);

    Page<Restaurant> findAllByNameContaining(String keyword, Pageable pageable);

    List<Restaurant> findAllByRegion(String region);
}
