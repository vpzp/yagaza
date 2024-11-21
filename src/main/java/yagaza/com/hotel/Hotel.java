package yagaza.com.hotel;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import yagaza.com.order.SiteOrder;

import java.util.List;


@Getter
@Setter
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hotelName;

    private String region;

    private Integer priceOnePerson;

    private Integer priceTwoPerson;

    private Integer priceThreePerson;

    private Integer priceFourPerson;

    private Integer priceFivePerson;

    private String img;

    private Double mapX;

    private Double mapY;

    private String type;

    private String checkInTime;

    @ManyToMany
    private List<SiteOrder> siteOrder;
}
