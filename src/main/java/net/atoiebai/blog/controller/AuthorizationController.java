package net.atoiebai.blog.controller;

import lombok.AllArgsConstructor;
import net.atoiebai.blog.model.user.BlogUser;
import net.atoiebai.blog.model.user.Sex;
//import net.atoiebai.blog.registration.RegistrationRequest;
import net.atoiebai.blog.service.registration.RegistrationService;
import net.atoiebai.blog.service.bloguser.BlogUsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
public class AuthorizationController {

    private final RegistrationService registrationService;
    private final BlogUsersService blogUsersService;

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
//    @PostMapping(URLS.registerUser)
//    public String getNewUser(
//            @ModelAttribute("newUser")
//            @Valid BlogUser user,
//            BindingResult bindingResult) {
//
//        if (!user.checkPassword()) {
//            bindingResult.addError(new FieldError("user", "password", "passwords are not match | пароли не совпадают"));
//        }
//
//        if (bindingResult.hasErrors()) {
//            return "auth/register-page";
//        }
//
//        blogUsersService.saveUser(user);
//
//        return "auth/login";
//    }

    @PostMapping(URLS.registerUser)
    public String register(@ModelAttribute("newUser")  BlogUser user) {
         registrationService.register(user);
         return "auth/login";
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam(name = "token" , required = false) String token) {
        return registrationService.confirmToken(token);
    }
}
