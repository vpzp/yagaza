package yagaza.com.post;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import yagaza.com.DataNotFoundException;
import yagaza.com.comment.Comment;
import yagaza.com.user.SiteUser;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Page<Post> getList(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDateTime"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

        return postRepository.findAll(pageable);
    }

    public Post getPost(Long id){
        Optional<Post> post = this.postRepository.findById(id);
        if (post.isPresent()){
            return post.get();
        }else{
            throw new DataNotFoundException("postFindById의 값이 존재하지 않습니다.");
        }
    }

    public void create(String subject, String content, SiteUser author){
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        Post post = new Post();
        post.setContent(content);
        post.setSubject(subject);
        post.setCreateDateTime(zonedDateTime.toLocalDateTime());
        post.setAuthor(author);
        this.postRepository.save(post);
    }
}
