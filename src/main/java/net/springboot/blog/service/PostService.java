package net.springboot.blog.service;

import net.springboot.blog.model.post.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();

    Post getPostById(long id);

    void deletePostById(long id);

    void savePost(Post post);
}
