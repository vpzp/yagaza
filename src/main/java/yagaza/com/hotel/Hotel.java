package yagaza.com.hotel;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import yagaza.com.review.HotelReview;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hotelName;

    private LocalDateTime reservationDate;

    private String region;

    @ManyToOne
    private HotelRoom hotelRoom;

    @ManyToOne
    private HotelReview hotelReviewId;

    //TODO 이미지 자료형 구현
}
