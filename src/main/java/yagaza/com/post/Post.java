package yagaza.com.post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import yagaza.com.comment.Comment;
import yagaza.com.user.SiteUser;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SiteUser author;

    @Column(columnDefinition = "TEXT")
    private String headLine;

    @Column(length = 40)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDateTime;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

}
