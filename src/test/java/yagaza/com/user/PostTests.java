package yagaza.com.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yagaza.com.post.Post;
import yagaza.com.post.PostRepository;

import java.time.LocalDateTime;

@SpringBootTest
public class PostTests {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void create(){
        Post p1 = new Post();
        p1.setSubject("제목");
        p1.setContent("내용");
        p1.setCreateDateTime(LocalDateTime.now());
        this.postRepository.save(p1);

    }
}
