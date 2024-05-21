package yagaza.com.Tourism;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourismRepository extends JpaRepository<Tourism, Long> {
}
