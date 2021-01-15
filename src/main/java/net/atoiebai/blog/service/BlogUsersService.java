package net.atoiebai.blog.service;

import net.atoiebai.blog.model.user.BlogUser;

import java.util.List;


public interface BlogUsersService {
    List<BlogUser> getAllUsers();

    void saveUser(BlogUser blogUser);

    BlogUser getUser(long id);

    void deleteUser(long id);

}
