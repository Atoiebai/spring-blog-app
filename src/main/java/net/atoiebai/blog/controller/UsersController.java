package net.atoiebai.blog.controller;

import lombok.AllArgsConstructor;
import net.atoiebai.blog.model.user.BlogUser;
import net.atoiebai.blog.service.bloguser.BlogUsersService;
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
@AllArgsConstructor
public class UsersController {

    private final BlogUsersService usersService;

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

    @GetMapping("/{username}")
    public String getUser(Model model,
                          @PathVariable(value = "username") String username) {

        try {
            BlogUser blogUser = usersService.getUser(username);
            model.addAttribute("user", blogUser);
            model.addAttribute("countOfPosts" , usersService.countPosts(blogUser.getId()));
        } catch (EntityNotFoundException e) {
//            TODO: logic for exception
        }

        return "views/get-user";
    }
}
