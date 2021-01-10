package net.springboot.blog.controller;

import net.springboot.blog.service.BlogUsersService;
import net.springboot.blog.service.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller only for  functions which available for admins
 * The main purpose of providing access to pages for managing the blog and its users.
 * Deleting posts, blocking accounts, etc.
 */

@Controller
@RequestMapping(URLS.adminPage)
public class AdminsController {
    private final BlogUsersService usersService;
    private final PostService postService;

    public AdminsController(BlogUsersService usersService, PostService postService) {
        this.usersService = usersService;
        this.postService = postService;
    }

    /**
     * A page which available only for users with special authorities
     *
     * @return admin-panel-page with all usable functions
     */

    @GetMapping()
    @PreAuthorize("hasAuthority('can:manage:users')")
    public String getSecretPage(Model model) {
        model.addAttribute("users", usersService.getAllUsers());
        model.addAttribute("posts", postService.getAllPosts());
        //TODO: secret page with functions which available only for admins
        return "views/hidden-page";
    }


}
