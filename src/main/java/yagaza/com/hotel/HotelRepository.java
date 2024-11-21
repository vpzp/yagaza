package yagaza.com.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Optional<Hotel> findByHotelName(String hotelName);

    List<Hotel> findTop10By();

    List<Hotel> findTop100By();

    List<Hotel> findAllByTypeContains(String type);
}
