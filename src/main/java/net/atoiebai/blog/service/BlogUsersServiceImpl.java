package net.atoiebai.blog.service;


import lombok.AllArgsConstructor;
import net.atoiebai.blog.email.EmailSender;
import net.atoiebai.blog.model.user.BlogUser;
import net.atoiebai.blog.model.user.Role;
import net.atoiebai.blog.model.user.Status;
import net.atoiebai.blog.registration.token.ConfirmationToken;
import net.atoiebai.blog.registration.token.ConfirmationTokenService;
import net.atoiebai.blog.repository.BlogUsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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