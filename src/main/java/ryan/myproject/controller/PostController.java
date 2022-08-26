package ryan.myproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ryan.myproject.entity.Post;
import ryan.myproject.repository.PostRepository;
import ryan.myproject.service.PostService;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/{memberId}")
    public String showPosts(@PathVariable long memberId, Model model){
        List<Post> postLists = postService.getPostLists(memberId);
        model.addAttribute("posts", postLists);
        model.addAttribute("memberId", memberId);
        return "post/show-posts";
    }

    @GetMapping("/posts/add/{memberId}")
    public String addPostForm(@PathVariable long memberId){
        return "post/add-post";
    }

    @PostMapping("/posts/add/{memberId}")
    public String addPost(@PathVariable long memberId, @RequestParam String postTitle, @RequestParam String postBody, RedirectAttributes ra){
        postService.addPost(memberId, postTitle, postBody);
        return "redirect:/posts/{memberId}";
    }

    @GetMapping("/posts/detail/{memberId}/{postId}")
    public String getDetailPost(@PathVariable long memberId, @PathVariable long postId, Model model){
        Post post = postService.getSinglePost(postId);
        model.addAttribute("post", post);
        model.addAttribute("memberId", memberId);
        return "/post/detail-post";
    }

    @PostMapping("/posts/delete/{memberId}/{postId}")
    public String deletePost(@PathVariable long memberId, @PathVariable long postId){
        postService.deletePost(postId);
        return "redirect:/posts/{memberId}";
    }

    @PostMapping("/posts/modify/{memberId}/{postId}")
    public String modifyPost(@PathVariable long postId, @RequestParam String postTitle, @RequestParam String postBody){
        postService.modifyPost(postId, postTitle, postBody);
        return "redirect:/posts/{memberId}";
    }

    @GetMapping("/posts/modify/{memberId}/{postId}")
    public String postForm(@PathVariable long postId, Model model){
        model.addAttribute("post", postService.getSinglePost(postId));
        return "post/modify-post";
    }


}
