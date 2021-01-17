package net.atoiebai.blog.service;

import net.atoiebai.blog.model.post.Category;
import net.atoiebai.blog.model.post.Post;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    void saveCategory(Category category);

}
