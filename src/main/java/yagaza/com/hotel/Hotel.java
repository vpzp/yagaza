package yagaza.com.hotel;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;
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

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany
    private List<HotelRoom> hotelRoom;

    private String price;

    private String img;
}
