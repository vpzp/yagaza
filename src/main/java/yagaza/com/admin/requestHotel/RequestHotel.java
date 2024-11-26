package yagaza.com.admin.requestHotel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Accessors(chain=true)
public class RequestHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hotelName;

    private String region;

    @ColumnDefault("0")
    private Integer priceOnePerson;
    @ColumnDefault("0")
    private Integer priceTwoPerson;
    @ColumnDefault("0")
    private Integer priceThreePerson;
    @ColumnDefault("0")
    private Integer priceFourPerson;
    @ColumnDefault("0")
    private Integer priceFivePerson;

    private String img;

    private String type;

    @ColumnDefault("")
    private String checkInTime;

    private String status;

    private String userId;
}
