package yagaza.com.admin.requestHotel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yagaza.com.hotel.Hotel;

@Repository
public interface RequestHotelRepository extends JpaRepository<RequestHotel, Long> {

    Page<RequestHotel> findAllByHotelNameContaining(String keyword, Pageable pageable);

    Page<RequestHotel> findAllByUserId(String userId, Pageable pageable);
}
