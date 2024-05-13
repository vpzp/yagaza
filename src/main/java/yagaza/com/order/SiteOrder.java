package yagaza.com.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import yagaza.com.hotel.Hotel;
import yagaza.com.restaurant.Restaurant;
import yagaza.com.user.SiteUser;

import java.util.List;

@Getter
@Setter
@Entity
public class SiteOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cash;

    private int prod;

    private String date;

    private String car;

    private String travel;

    @ManyToMany
    private List<Restaurant> restaurant;

    @ManyToMany
    private List<Hotel> hotel;

    @ManyToOne
    private SiteUser siteUser;

}
