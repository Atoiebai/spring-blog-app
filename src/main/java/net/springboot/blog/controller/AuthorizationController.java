package net.springboot.blog.controller;

import net.springboot.blog.model.user.BlogUser;
import net.springboot.blog.service.BlogUsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class AuthorizationController {

   private final BlogUsersService blogUsersService;

    public AuthorizationController(BlogUsersService blogUsersService) {
        this.blogUsersService = blogUsersService;
    }

    //return s login-form
    @GetMapping(URLS.login)
    public String getLoginPage() {
        return "auth/login";
    }

    //returns Sign up form for new user
    @GetMapping(URLS.registerUser)
    public String registerNewUser(Model model) {
         model.addAttribute("newUser" , new BlogUser());
        return "auth/register-page";
    }

    //creates new user based on entered data and redirects to login page
    @PostMapping(URLS.registerUser)
    public String getNewUser(
            @ModelAttribute("newUser")
            @Valid BlogUser user ,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "auth/register-page";
        }
        blogUsersService.saveUser(user);

        return "auth/login";
    }
}
