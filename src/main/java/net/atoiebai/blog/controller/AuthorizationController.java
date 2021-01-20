package net.atoiebai.blog.controller;

import lombok.AllArgsConstructor;
import net.atoiebai.blog.model.user.BlogUser;
import net.atoiebai.blog.model.user.Sex;
import net.atoiebai.blog.service.bloguser.BlogUsersService;
import net.atoiebai.blog.service.registration.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class AuthorizationController {

    private final RegistrationService registrationService;
    private final BlogUsersService blogUsersService;

    //returns login-form
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

    /*
     checks if all attributes is correct ,
     and creating User with minimal authorities and role GUEST
     */

    @PostMapping(URLS.registerUser)
    public String register(@ModelAttribute("newUser")
                           @Valid BlogUser user,
                           BindingResult bindingResult) {

        if (blogUsersService.userExist(user.getEmail()) ||
                blogUsersService.userExist(user.getUsername())) {
            bindingResult.addError(new FieldError("user", "email", "email or username already in use | почта или юзернейм уже используется"));
        }

        if (!user.checkPassword()) {
            bindingResult.addError(new FieldError("user", "password", "passwords are not match | пароли не совпадают"));
        }

        if (bindingResult.hasErrors()) {
            return "auth/register-page";
        }

        registrationService.register(user);
        return "auth/login";

    }

    // after confirming email user gets base-permissions and role USER
    @GetMapping(path = "/confirm")
    public String confirm(Model model, @RequestParam(name = "token", required = false) String token) {
        model.addAttribute("confirmToken", registrationService.confirmToken(token));
        return "views/confirm";
    }
}
