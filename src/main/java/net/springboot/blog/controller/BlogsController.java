package net.springboot.blog.controller;

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

    @Value("${spring.datasource.username}")
    String user; //username for database connection
    @Value("${spring.datasource.password}")
    String password; //password for database connection
    @Value("${spring.datasource.url}")
    String urlDb; //url for database connection

    public final JdbcTemplate jdbcTemplate;

    public BlogsController(JdbcTemplate jdbcTemplate, PostService postService) {
        this.jdbcTemplate = jdbcTemplate;
        this.postService = postService;
    }


    @GetMapping()
    @PreAuthorize("hasAuthority('can:read')") //access only for authorized users
    public String showBlog(Model model) {
        //TODO: main page of blog
        model.addAttribute("listOfPosts", postService.getAllPosts());
        return "views/blog";
    }

}
