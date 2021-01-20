package net.atoiebai.blog.controller;

import lombok.AllArgsConstructor;
import net.atoiebai.blog.service.category.CategoryService;
import net.atoiebai.blog.service.post.PostService;
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
        model.addAttribute("listOfPosts", postService.getAllPosts());
        return "views/blog-copy";
    }

    @GetMapping(URLS.categoryPage)
    public String showCategories(Model model) {
        model.addAttribute("categories" , categoryService.getAllCategories());
        return "views/categories";
    }

    @ModelAttribute
    public void listOfCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
    }

}
