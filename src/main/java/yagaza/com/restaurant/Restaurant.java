package yagaza.com.restaurant;

import jakarta.persistence.*;
import lombok.Getter;
import yagaza.com.review.RestaurantReview;

@Entity
@Getter
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String region;

    private String name;

    @ManyToOne
    private RestaurantReview restaurantReview;

    //TODO 위치 정보 설정 해야함
    //TODO 이미지사진 자료형 설정
}
