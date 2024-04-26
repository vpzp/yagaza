package yagaza.com.comment;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yagaza.com.post.Post;
import yagaza.com.post.PostService;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Long id,
                               @RequestParam(value = "content") String content){
        Post post = this.postService.getPost(id);
        this.commentService.create(post, content);

        return String.format("redirect:/post/detail/%s", id);
    }

}
