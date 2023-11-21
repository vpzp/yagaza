package yagaza.com.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    //사용자ID로 SiteUser 엔티티 반환
    Optional<SiteUser> findById(String Id);
}
