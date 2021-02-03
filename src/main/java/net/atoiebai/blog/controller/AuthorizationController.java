package net.atoiebai.blog.controller;

import lombok.AllArgsConstructor;
import net.atoiebai.blog.service.registration.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class AuthorizationController {

    private final RegistrationService registrationService;

    //returns login-form
    @GetMapping(URLS.login)
    public String getLoginPage() {
        return "auth/login";
    }

    // after confirming email user gets base-permissions and role USER
    @GetMapping(path = "/confirm")
    public String confirm(Model model, @RequestParam(name = "token", required = false) String token) {
        model.addAttribute("confirmToken", registrationService.confirmToken(token));
        return "views/confirm";
    }
}
