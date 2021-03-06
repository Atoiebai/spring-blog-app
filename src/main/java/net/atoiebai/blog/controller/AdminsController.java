package net.atoiebai.blog.controller;

import lombok.AllArgsConstructor;
import net.atoiebai.blog.model.post.Category;
import net.atoiebai.blog.service.bloguser.BlogUsersService;
import net.atoiebai.blog.service.category.CategoryService;
import net.atoiebai.blog.service.post.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

/**
 * Controller only for  functions which available for admins
 * The main purpose of providing access to pages for managing the blog and its users.
 * Deleting posts, blocking accounts, etc.
 */

@Controller
@RequestMapping(URLS.adminPage)
@AllArgsConstructor
// TODO: 2/5/2021 Secure by role_admin 
//@Secured("ROLE_ADMIN")
public class AdminsController {
    // TODO: 2/5/2021 Methods to manage users posts and blog 
    private final BlogUsersService usersService;
    private final PostService postService;
    private final CategoryService categoryService;


    /*  A page which available only for users with special authorities
            @return admin-panel-page with all usable functions */
    @GetMapping()
    @PreAuthorize("hasAuthority('can:manage:users')")
    public String getSecretPage(Model model) {
        //TODO: secret page with functions which available only for admins
        model.addAttribute("users", usersService.getAllUsers());
        model.addAttribute("posts", postService.getAllPosts());
        return "views/hidden-page";
    }

    
    @GetMapping("/create-new-category")
    @PreAuthorize("hasAuthority('can:manage:users')")
    public String createCategory(Model model) {
        model.addAttribute("newCategory", new Category());
        return "views/category-save-form";
    }

    @PostMapping("/create-new-category")
    @PreAuthorize("hasAuthority('can:manage:users')")
    public String saveCategory(@ModelAttribute("newCategory") Category category) {
        category.setSlug(category.getTitle().replaceAll("\\s", "-").toLowerCase(Locale.ROOT));
        categoryService.saveCategory(category);
        return "redirect:/blog/categories";
    }
}
