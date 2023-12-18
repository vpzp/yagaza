package yagaza.com.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<SiteUser, Long> {
    //사용자ID로 SiteUser 엔티티 반환
    Optional<SiteUser> findById(String Id);

}
