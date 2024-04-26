package yagaza.com.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findBySubject(String subject);

    Post findBySubjectAndContent(String subject, String content);

    Post findBySubjectLike(String subject);
}
