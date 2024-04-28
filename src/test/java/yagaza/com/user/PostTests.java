package yagaza.com.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yagaza.com.post.Post;
import yagaza.com.post.PostRepository;
import yagaza.com.post.PostService;

import java.time.LocalDateTime;

@SpringBootTest
public class PostTests {
    @Autowired
    private PostService postService;

    @Test
    public void createPost(){
        for(int i = 1 ; i <= 200 ; i++){
            String subject = String.format("테스트 데이터 : [%03d]", i);
            String content = String.format("%d번째 게시물 입니다.", i);
            this.postService.create(subject, content, null);
        }
    }
}
