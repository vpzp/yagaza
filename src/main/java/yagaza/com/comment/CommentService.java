package yagaza.com.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yagaza.com.post.Post;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public void create(Post post, String content){
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreateDateTime(LocalDateTime.now());
        comment.setPost(post);
        this.commentRepository.save(comment);
    }
}
