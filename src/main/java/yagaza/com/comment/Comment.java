package yagaza.com.comment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import yagaza.com.post.Post;
import yagaza.com.user.SiteUser;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SiteUser author;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDateTime;

    @ManyToOne
    private Post post;
}
