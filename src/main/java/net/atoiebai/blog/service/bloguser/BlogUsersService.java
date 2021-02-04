package net.atoiebai.blog.service.bloguser;

import net.atoiebai.blog.model.user.BlogUser;

import java.util.List;


public interface BlogUsersService {

    List<BlogUser> getAllUsers();

    void saveUser(BlogUser blogUser);

    BlogUser getUser(long id);

    BlogUser getUser(String identifier);

    // TODO: 2/4/2021 ban user instead deleting 
    void deleteUser(long id);

    void enableBlogUser(String email);

    boolean userExist(String email);

    Integer countPosts(Long id);

}
