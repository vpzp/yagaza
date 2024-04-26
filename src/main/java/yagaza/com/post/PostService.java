package yagaza.com.post;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yagaza.com.comment.Comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getList(){
        return postRepository.findAll();
    }

    public Post getPost(Long id){
        Optional<Post> post = this.postRepository.findById(id);
        return post.get();
    }

    public void create(String subject, String content){
        Post post = new Post();
        post.setContent(content);
        post.setSubject(subject);
        post.setCreateDateTime(LocalDateTime.now());
        this.postRepository.save(post);
    }
}
