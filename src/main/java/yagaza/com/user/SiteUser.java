package yagaza.com.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import yagaza.com.order.SiteOrder;

import java.util.List;

@Getter
@Setter
@Entity
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long realId;

    @Column(unique = true)
    private String id;

    private String name;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    private String phoneNumber;

    @OneToMany
    private List<SiteOrder> siteOrder;

}
