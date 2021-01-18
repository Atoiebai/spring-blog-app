package net.atoiebai.blog.service.bloguser;

import net.atoiebai.blog.model.user.BlogUser;

import java.util.List;


public interface BlogUsersService {
    List<BlogUser> getAllUsers();

    void saveUser(BlogUser blogUser);

    BlogUser getUser(long id);

    void deleteUser(long id);

    int enableBlogUser(String email);
}
