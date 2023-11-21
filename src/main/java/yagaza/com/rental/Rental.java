package yagaza.com.rental;

import jakarta.persistence.*;
import lombok.Getter;
import yagaza.com.review.RentalReview;

@Entity
@Getter
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String rentalType;

    private String name;

    private String price;

    @ManyToOne
    private RentalReview rentalReview;
}
