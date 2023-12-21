package yagaza.com.hotel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class HotelRoom {
    @Id
    private String id;

    private String href;

    private String checkInTime;

    private String name;

    private LocalDateTime reservationTime;

    private String price;

    private String content;

    @ManyToOne
    private Hotel hotel;

}
