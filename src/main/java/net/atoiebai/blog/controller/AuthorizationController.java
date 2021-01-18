package net.atoiebai.blog.controller;

import lombok.AllArgsConstructor;
import net.atoiebai.blog.model.user.BlogUser;
import net.atoiebai.blog.model.user.Sex;
import net.atoiebai.blog.repository.BlogUsersRepository;
import net.atoiebai.blog.service.BlogUsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
@AllArgsConstructor
public class AuthorizationController {

    private final BlogUsersService blogUsersService;
    private final BlogUsersRepository blogUsersRepository;

    //return s login-form
    @GetMapping(URLS.login)
    public String getLoginPage() {
        return "auth/login";
    }

    //returns Sign up form for new user
    @GetMapping(URLS.registerUser)
    public String registerNewUser(Model model) {
        model.addAttribute("newUser", new BlogUser());
        model.addAttribute("sex", Sex.values());
        return "auth/register-page";
    }

    //creates new user based on entered data and redirects to login page
    @PostMapping(URLS.registerUser)
    public String getNewUser(
            @ModelAttribute("newUser")
            @Valid BlogUser user,
            BindingResult bindingResult) {

        if (!user.checkPassword()) {
            bindingResult.addError(new FieldError("user", "password", "passwords are not match | пароли не совпадают"));
        }

        if (bindingResult.hasErrors()) {
            return "auth/register-page";
        }

        blogUsersService.saveUser(user);

        return "auth/login";
    }
}
