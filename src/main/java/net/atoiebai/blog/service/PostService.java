package net.atoiebai.blog.service;

import net.atoiebai.blog.model.post.Category;
import net.atoiebai.blog.model.post.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();

    Post getPostById(long id);

    void deletePostById(long id);

    void savePost(Post post);

    List<Post> getAllByCategory(Category category);
}
