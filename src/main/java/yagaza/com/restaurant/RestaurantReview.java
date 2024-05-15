package yagaza.com.restaurant;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import yagaza.com.user.SiteUser;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class RestaurantReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    private SiteUser author;

    private double score;
}
