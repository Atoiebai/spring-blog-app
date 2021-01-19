package net.atoiebai.blog.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@AllArgsConstructor
public class MainPageController {

    //returns  main page of a website
    @GetMapping(URLS.homePage)
    @PreAuthorize("hasAuthority('can:read')")
    public String getStartPage() {
        return "views/start-page";
    }

}