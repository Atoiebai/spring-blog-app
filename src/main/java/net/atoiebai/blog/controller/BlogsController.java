package net.atoiebai.blog.controller;

import lombok.AllArgsConstructor;
import net.atoiebai.blog.service.CategoryService;
import net.atoiebai.blog.service.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(URLS.blogPage)
@AllArgsConstructor
public class BlogsController {

    public final PostService postService;
    public final CategoryService categoryService;

    @GetMapping()
    @PreAuthorize("hasAuthority('can:read')") //access only for authorized users
    public String showBlog(Model model) {
        //TODO: main page of blog
        model.addAttribute("listOfPosts", postService.getAllPosts());
        return "views/blog-copy";
    }

    @GetMapping(URLS.categoryPage)
    public String showCategories(Model model) {

        return "views/category";
    }

    @ModelAttribute
    public void listOfCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories() );
    }

}
