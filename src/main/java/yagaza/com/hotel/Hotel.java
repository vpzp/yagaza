package yagaza.com.hotel;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;
import yagaza.com.order.SiteOrder;
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

    private Integer priceOnePerson;

    private Integer priceTwoPerson;

    private Integer priceThreePerson;

    private Integer priceFourPerson;

    private Integer priceFivePerson;

    private String img;

    private String type;

    private List<String> keyword;

    @ManyToMany
    private List<SiteOrder> siteOrder;
}
