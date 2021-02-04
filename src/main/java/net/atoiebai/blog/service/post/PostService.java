package net.atoiebai.blog.service.post;

import net.atoiebai.blog.model.post.Category;
import net.atoiebai.blog.model.post.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();

    Post getPostById(long id);

    // TODO: 2/4/2021 delete posts 
    void deletePostById(long id);

    void savePost(Post post);

    List<Post> getAllByCategory(Category category);

    Post getPostBySlug(String slug);

}
