package net.atoiebai.blog.controller;

import lombok.AllArgsConstructor;
import net.atoiebai.blog.service.post.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class CategoryController {
    // TODO: 2/5/2021 Get all posts by category 
    private final PostService postService;

    @GetMapping("/{category}/{slug}")
    @PreAuthorize("hasAuthority('can:read')")
    public String getPost(Model model, @PathVariable(value = "slug") String slug, @PathVariable String category) {
        model.addAttribute("post", postService.getPostBySlug(slug));
        return "views/get-post";
    }
}
