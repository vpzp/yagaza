package yagaza.com.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findBySubject(String subject);

    Post findBySubjectAndContent(String subject, String content);

    List<Post> findBySubjectLike(String subject);

    Page<Post> findAll(Pageable pageable);

}
