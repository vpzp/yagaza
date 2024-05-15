package yagaza.com.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yagaza.com.post.Post;
import yagaza.com.user.SiteUser;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment create(Post post, String content, SiteUser author){
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreateDateTime(LocalDateTime.now());
        comment.setPost(post);
        comment.setAuthor(author);
        this.commentRepository.save(comment);
        return comment;
    }
}
