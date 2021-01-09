package net.springboot.blog.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  Controller only for  functions which available for admins
 *  The main purpose of providing access to pages for managing the blog and its users.
 *  Deleting posts, blocking accounts, etc.
 */

@Controller
@RequestMapping(URLS.adminPage)
public class AdminsController {

    /**
     * A page which available only for users with special authorities
     * @return admin-panel-page with all usable functions
     */

    @GetMapping()
    @PreAuthorize("hasAuthority('can:write')")
    public String getSecretPage() {
        //TODO: secret page with functions which available only for admins
        return "views/hidden-page";
    }


}
