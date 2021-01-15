package net.springboot.blog.controller;

import net.springboot.blog.model.post.Category;
import net.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(URLS.blogPage)
public class BlogsController {

    public final PostService postService;

    public BlogsController( PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('can:read')") //access only for authorized users
    public String showBlog(Model model) {
        //TODO: main page of blog
        model.addAttribute("listOfPosts", postService.getAllPosts());
        model.addAttribute("categories" , Category.values());
        return "views/blog-copy";
    }

    @GetMapping(URLS.categoryPage)
    public String showCategories(Model model) {

        return "views/category";
    }

}
