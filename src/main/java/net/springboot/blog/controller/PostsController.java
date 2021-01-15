package net.springboot.blog.controller;

import net.springboot.blog.model.post.Category;
import net.springboot.blog.model.post.Post;
import net.springboot.blog.model.user.BlogUser;
import net.springboot.blog.service.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(URLS.postsPage)
public class PostsController {

    private final PostService postService;

    public PostsController(PostService postService) {
        this.postService = postService;

    }

    //page to create new Post and send it on the server via POST method under it
    @GetMapping(URLS.createPost)
    public String createPost(Model model) {
        model.addAttribute("newPost", new Post());
        model.addAttribute("categories", Category.values());
        return "views/create-post";
    }


    @PostMapping(URLS.createPost)
    public String addPost(@ModelAttribute("newPost") Post post) {

        BlogUser user = (BlogUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user == null) throw new NullPointerException("User is null sorry");

        post.setBlogUser(user);

        postService.savePost(post);

        return "views/blog";
    }


    @GetMapping()
    @PreAuthorize("hasAuthority('can:read')")
    public String getAllPosts(Model model) {

        model.addAttribute("posts", postService.getAllPosts());

        return "views/get-posts";

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('can:read')")
    public String getPost(Model model, @PathVariable(value = "id") long id) {

        model.addAttribute("post", postService.getPostById(id));

        return "views/get-post";

    }

}
