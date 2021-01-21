package net.atoiebai.blog.service.post;

import net.atoiebai.blog.model.post.Category;
import net.atoiebai.blog.model.post.Post;
import net.atoiebai.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(long id) {
        return postRepository.getOne(id);
    }

    @Override
    public void deletePostById(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public void savePost(Post post) {
        post.setSlug(post.getTheme().replaceAll("\\s", "-").toLowerCase(Locale.ROOT));
        postRepository.save(post);
    }

    @Override
    public List<Post> getAllByCategory(Category category) {
        return postRepository.findAllByCategory(category);
    }

    @Override
    public Post getPostBySlug(String slug) {
        return postRepository.findBySlug(slug).orElseThrow();
    }

}