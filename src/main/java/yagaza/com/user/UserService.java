package yagaza.com.user;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String id, String name, String username, String password, String email, String phoneNumber){
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

}
