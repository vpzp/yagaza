package yagaza.com.Tourism;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import yagaza.com.order.SiteOrder;

import java.util.List;

@Entity
@Getter
@Setter
public class Tourism {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 1000)
    private String imgUrl;

    @Column(length = 1000)
    private String content;

    private String region;

    private String address;

    private int price;

    private String type;

//    @ManyToMany (cascade = CascadeType.REMOVE)
//    private List<SiteOrder> siteOrder;
}
