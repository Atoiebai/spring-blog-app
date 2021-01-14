package net.springboot.blog.controller;

import net.springboot.blog.model.user.BlogUser;
import net.springboot.blog.service.BlogUsersService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityNotFoundException;

/**
 * Controller which returns pages for simple users
 * to communicate with their side of blog
 */

@Controller
@RequestMapping(URLS.usersPage)
public class UsersController {

    private final BlogUsersService usersService;

    public UsersController(BlogUsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", usersService.getAllUsers());
        return "views/get-users";
    }

    @GetMapping(URLS.personalPage)
    @PreAuthorize("hasAuthority('can:write')")
    public String getPersonalPage(Model model) {
        BlogUser user = (BlogUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("currentUser", user);
        return "views/personal-page";
    }

    @GetMapping("/{id}")
    public String getUser(Model model,
                          @PathVariable(value = "id") long id) {
        try {
            BlogUser blogUser = usersService.getUser(id);
            model.addAttribute("user", blogUser);

        } catch (EntityNotFoundException e) {
            model.addAttribute("message", "No user with such id. Try to use another one");
        }

        return "views/get-user";

    }
}
