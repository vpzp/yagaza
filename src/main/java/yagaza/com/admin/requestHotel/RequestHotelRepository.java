package yagaza.com.admin.requestHotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestHotelRepository extends JpaRepository<RequestHotel, Long> {


}
