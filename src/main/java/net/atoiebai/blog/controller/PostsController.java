package net.atoiebai.blog.controller;

import lombok.AllArgsConstructor;
import net.atoiebai.blog.model.post.Post;
import net.atoiebai.blog.model.user.BlogUser;
import net.atoiebai.blog.service.category.CategoryService;
import net.atoiebai.blog.service.post.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(URLS.postsPage)
@AllArgsConstructor
public class PostsController {

    private final PostService postService;
    private final CategoryService categoryService;

    //page to create new Post and send it on the server via POST method under it
    @GetMapping(URLS.createPost)
    public String createPost(Model model) {
        model.addAttribute("newPost", new Post());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "views/create-post";
    }


    @PostMapping(URLS.createPost)
    public String addPost(@ModelAttribute("newPost") Post post) {
        BlogUser user = (BlogUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) throw new NullPointerException("User is null sorry");
        post.setBlogUser(user);
        postService.savePost(post);
        return "redirect:/blog";
    }


    @GetMapping()
    @PreAuthorize("hasAuthority('can:read')")
    public String getAllPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "views/get-posts";
    }

}
