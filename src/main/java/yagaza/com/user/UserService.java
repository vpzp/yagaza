package yagaza.com.user;


import lombok.RequiredArgsConstructor;
import org.hibernate.exception.DataException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String id, String name, String username, String password,
                           String email, String phoneNumber){
        SiteUser user = new SiteUser();
        user.setId(id);
        user.setUsername(username);
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        this.userRepository.save(user);

        return user;
    }

    public SiteUser modify(){
        SiteUser user = new SiteUser();
        return user;
    }

    public SiteUser getUser(String id) throws Exception {
        Optional<SiteUser> siteUser = this.userRepository.findById(id);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new Exception("siteUser객체가 없습니다");
        }
    }
}
