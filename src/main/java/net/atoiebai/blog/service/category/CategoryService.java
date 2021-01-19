package net.atoiebai.blog.service.category;

import net.atoiebai.blog.model.post.Category;

import java.util.List;


public interface CategoryService {

    List<Category> getAllCategories();

    void saveCategory(Category category);

}
