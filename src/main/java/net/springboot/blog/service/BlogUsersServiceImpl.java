package net.springboot.blog.service;

import net.springboot.blog.dao.BlogUserFindIdRowMapper;
import net.springboot.blog.model.user.BlogUser;
import net.springboot.blog.model.user.Role;
import net.springboot.blog.model.user.Status;
import net.springboot.blog.repository.BlogUsersRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogUsersServiceImpl implements BlogUsersService {

    private final BlogUsersRepository blogUsersRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final JdbcTemplate jdbcTemplate;

    public BlogUsersServiceImpl(BlogUsersRepository blogUsersRepository, PasswordEncoder bCryptPasswordEncoder, JdbcTemplate jdbcTemplate) {
        this.blogUsersRepository = blogUsersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BlogUser> getAllUsers() {
        return blogUsersRepository.findAll();
    }

    @Override
    public void saveUser(BlogUser blogUser) {
        blogUser.setPassword(bCryptPasswordEncoder.encode(blogUser.getPassword()));
        blogUser.setRole(Role.USER);
        blogUser.setStatus(Status.ACTIVE);
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
    public Long getAuthorizedUserId(String identifier) {
        return jdbcTemplate.query("SELECT id FROM users WHERE email=?",
                new Object[]{identifier}, new BlogUserFindIdRowMapper())
                .stream().findFirst().orElseThrow(null);
    }

}