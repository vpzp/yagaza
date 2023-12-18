package yagaza.com.hotel;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import yagaza.com.review.HotelReview;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Entity
public class Hotel {
    @Id
    private Long id;

    private String hotelHref;

    private String hotelName;

    private String region;

    @OneToMany
    private List<HotelRoom> hotelRoom;

//    @ManyToOne
//    private HotelReview hotelReviewId;

    private String img;
}
