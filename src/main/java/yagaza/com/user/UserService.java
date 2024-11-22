package yagaza.com.user;


import lombok.RequiredArgsConstructor;
import org.hibernate.exception.DataException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yagaza.com.DataNotFoundException;

import java.util.ArrayList;
import java.util.List;
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

    public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("siteUser객체가 없습니다");
        }
    }

    public Page<SiteUser> getUserList(int page, String keyword){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("realId"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

        return userRepository.findAllByIdContainingAndAuthorityNot(keyword, "관리자", pageable);
    }

    public SiteUser updateAuthority(String id, String authority){
        Optional<SiteUser> optionalSiteUser = userRepository.findById(id);
        if (optionalSiteUser.isPresent()){
            SiteUser siteUser = optionalSiteUser.get();
            siteUser.setAuthority(authority);
            if (authority.equals("'파트너'")){
                siteUser.setCreatePost(true);
            }
            userRepository.save(siteUser);
            return siteUser;
        }else {
            throw new DataNotFoundException("siteUser객체가 없습니다");
        }

    }
}
