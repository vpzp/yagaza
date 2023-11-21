package yagaza.com.review;

import jakarta.persistence.*;
import yagaza.com.user.SiteUser;

import java.time.LocalDateTime;

public class hotelReview {
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
