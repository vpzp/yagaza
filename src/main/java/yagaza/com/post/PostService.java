package yagaza.com.post;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import yagaza.com.comment.Comment;
import yagaza.com.user.SiteUser;

import java.time.LocalDateTime;
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
        return this.postRepository.findAll(pageable);
    }

    public Post getPost(Long id){
        Optional<Post> post = this.postRepository.findById(id);
        return post.get();
    }

    public void create(String subject, String content, String headLine, SiteUser author){
        Post post = new Post();
        post.setContent(content);
        post.setSubject(subject);
        post.setCreateDateTime(LocalDateTime.now());
        post.setAuthor(author);
        post.setHeadLine(headLine);
        this.postRepository.save(post);
    }
}
