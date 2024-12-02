package yagaza.com.Tourism;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourismRepository extends JpaRepository<Tourism, Long> {
    List<Tourism> findAllByRegion(String region);
}
