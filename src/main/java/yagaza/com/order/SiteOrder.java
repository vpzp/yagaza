package yagaza.com.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import yagaza.com.user.SiteUser;

@Getter
@Setter
@Entity
public class SiteOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cash;

    private String prod;

    private String date;

    private String car;

    private String travel;

    @ManyToOne
    private SiteUser siteUser;

}
