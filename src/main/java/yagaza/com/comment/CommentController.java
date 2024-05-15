package yagaza.com.comment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import yagaza.com.post.Post;
import yagaza.com.post.PostService;
import yagaza.com.user.SiteUser;
import yagaza.com.user.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final PostService postService;
    private final CommentService commentService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Long id,
                               @Valid CommentForm commentForm, BindingResult bindingResult,
                               Principal principal){
        Post post = this.postService.getPost(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        if (bindingResult.hasErrors()){
            model.addAttribute("post", post);
            return "post_detail";
        }
        this.commentService.create(post, commentForm.getContent(), siteUser);

        return String.format("redirect:/post/detail/%s", id);
    }

}
