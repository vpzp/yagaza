package yagaza.com.rental;


import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class RentalCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String type;

    private String name;

    private LocalDateTime reservationTime;

    private long price;

    private String content;
}
