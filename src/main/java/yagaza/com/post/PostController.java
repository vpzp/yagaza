package yagaza.com.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yagaza.com.comment.Comment;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/list")
    public String list(Model model){
        List<Post> postList = this.postService.getList();
        model.addAttribute("postList", postList);

        return "post_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id){
        Post post = this.postService.getPost(id);
        model.addAttribute("post", post);
        return "post_detail";
    }

    @GetMapping("/create")
    public String postCreate(){
        return "post_form";
    }

    @PostMapping("/create")
    public String postCreate(@RequestParam("subject") String subject, @RequestParam("content") String content){
        this.postService.create(subject, content);
        return "redirect:/post/list";
    }
}
