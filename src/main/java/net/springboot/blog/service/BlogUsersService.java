package net.springboot.blog.service;

import java.util.List;

import net.springboot.blog.model.user.BlogUser;


public interface BlogUsersService {
	List<BlogUser> getAllUsers();
	void saveUser(BlogUser blogUser);
	BlogUser getUser(long id);
	void deleteUser(long id);
	Long getAuthorizedUserId(String identifier);
}
