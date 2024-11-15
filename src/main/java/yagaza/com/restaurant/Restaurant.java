package yagaza.com.restaurant;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import yagaza.com.order.SiteOrder;

import java.util.List;

@Entity
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String region;

    private String name;

    private String content;

    //인덱스 0은 점심 가격 1은 저녁 가격
    private int[] price;

    private List<String> openTime;

    //음식 종류(한식, 일식, 기타 세계 음식)
    private String type;

    private List<String> img;

    @ManyToOne
    private RestaurantReview restaurantReview;

    @ManyToMany
    private List<SiteOrder> siteOrder;

}
