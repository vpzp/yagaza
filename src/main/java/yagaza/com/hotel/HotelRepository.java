package yagaza.com.hotel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Optional<Hotel> findByHotelNameAndRegion(String hotelName, String region);

    List<Hotel> findTop10By();

    List<Hotel> findTop100By();

    List<Hotel> findAllByTypeContainsAndRegion(String type, String region);

    List<Hotel> findAllByTypeContains(String type);

    Page<Hotel> findAllByHotelNameContaining(String keyword, Pageable pageable);

}
