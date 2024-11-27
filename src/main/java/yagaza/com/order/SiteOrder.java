package yagaza.com.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import yagaza.com.Tourism.Tourism;
import yagaza.com.hotel.Hotel;
import yagaza.com.plan.Plan;
import yagaza.com.restaurant.Restaurant;
import yagaza.com.survey.Survey;
import yagaza.com.user.SiteUser;

import java.time.LocalDateTime;
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

    private int date;

    private String car;

    private String travel;

    @ManyToMany
    private List<Restaurant> restaurant;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Tourism> tourism;

    @ManyToMany
    private List<Hotel> hotel;

    @ManyToOne
    private SiteUser siteUser;

    @OneToOne(orphanRemoval = true)
    private Survey survey;

    @OneToOne
    private Plan plan;

    private LocalDateTime createDateTime;
}
