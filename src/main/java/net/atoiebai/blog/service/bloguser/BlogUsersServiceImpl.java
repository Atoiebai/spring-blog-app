package net.atoiebai.blog.service.bloguser;


import lombok.AllArgsConstructor;
import net.atoiebai.blog.model.user.BlogUser;
import net.atoiebai.blog.repository.BlogUsersRepository;
import net.atoiebai.blog.service.registration.ConfirmationTokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlogUsersServiceImpl implements BlogUsersService {

    private final BlogUsersRepository blogUsersRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

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
        return this.blogUsersRepository.getOne(id);
    }

    @Override
    public void deleteUser(long id) {
        this.blogUsersRepository.deleteById(id);
    }

    @Override
    public int enableBlogUser(String email) {
        return blogUsersRepository.enableBlogUser(email);
    }

}