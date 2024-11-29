package yagaza.com.order;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import yagaza.com.Tourism.Tourism;
import yagaza.com.hotel.Hotel;
import yagaza.com.plan.Plan;
import yagaza.com.restaurant.Restaurant;
import yagaza.com.survey.Survey;
import yagaza.com.user.SiteUser;

import java.io.IOException;
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
    private String travel;

    @Lob
    private String restaurantJson; // JSON 데이터를 직접 저장

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

    // JSON -> List<List<Restaurant>>
    @Transient
    public List<List<Restaurant>> getRestaurantList() throws IOException {
        if (this.restaurantJson == null || this.restaurantJson.isEmpty()) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(this.restaurantJson, new TypeReference<List<List<Restaurant>>>() {});
    }

    // List<List<Restaurant>> -> JSON
    @Transient
    public void setRestaurantList(List<List<Restaurant>> restaurantList) throws JsonProcessingException {
        if (restaurantList == null) {
            this.restaurantJson = null;
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            this.restaurantJson = objectMapper.writeValueAsString(restaurantList);
        }
    }
}
