package net.atoiebai.blog.controller;

import lombok.AllArgsConstructor;
import net.atoiebai.blog.model.post.Category;
import net.atoiebai.blog.service.bloguser.BlogUsersService;
import net.atoiebai.blog.service.category.CategoryService;
import net.atoiebai.blog.service.post.PostService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller only for  functions which available for admins
 * The main purpose of providing access to pages for managing the blog and its users.
 * Deleting posts, blocking accounts, etc.
 */

@Controller
@RequestMapping(URLS.adminPage)
@AllArgsConstructor
@Secured("ADMIN")
public class AdminsController {

    private final BlogUsersService usersService;
    private final PostService postService;
    private final CategoryService categoryService;


/*      A page which available only for users with special authorities
        @return admin-panel-page with all usable functions */
    @GetMapping()
    public String getSecretPage(Model model) {
        //TODO: secret page with functions which available only for admins
        model.addAttribute("users", usersService.getAllUsers());
        model.addAttribute("posts", postService.getAllPosts());
        return "views/hidden-page";
    }


    @GetMapping("/create-new-category")
    public String createCategory(Model model) {
        model.addAttribute("newCategory", new Category());
        return "views/category-save-form";
    }

    @PostMapping("/create-new-category")
    public String saveCategory(@ModelAttribute("newCategory") Category category) {
        categoryService.saveCategory(category);
        return "redirect:/blog/category";
    }
}
