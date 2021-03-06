package net.atoiebai.blog.service.bloguser;


import lombok.AllArgsConstructor;
import net.atoiebai.blog.model.user.BlogUser;
import net.atoiebai.blog.repository.BlogUsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlogUsersServiceImpl implements BlogUsersService {

    private final BlogUsersRepository blogUsersRepository;

    @Override
    public List<BlogUser> getAllUsers() {
        return blogUsersRepository.findAll();
    }

    @Override
    public void saveUser(BlogUser blogUser) {
        this.blogUsersRepository.save(blogUser);
    }

    @Override
    public BlogUser getUser(long id) {
        return blogUsersRepository.findById(id).get();
    }

    @Override
    public BlogUser getUser(String identifier) {
        return blogUsersRepository.findByUsername(identifier)
                .orElseGet(() -> blogUsersRepository
                        .findByEmail(identifier).orElseThrow());
    }

    @Override
    public void deleteUser(long id) {
        this.blogUsersRepository.deleteById(id);
    }

    @Override
    public void enableBlogUser(String email) {
        blogUsersRepository.enableBlogUser(email);
    }

    @Override
    public boolean userExist(String email) {
        return blogUsersRepository.findByEmail(email).isPresent()
                || blogUsersRepository.findByUsername(email).isPresent();
    }

    @Override
    public Integer countPosts(Long id) {
        return blogUsersRepository.countPostsOfUser(id);
    }
}