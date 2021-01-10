package net.springboot.blog.security;

import net.springboot.blog.model.user.BlogUser;
import net.springboot.blog.repository.BlogUsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final BlogUsersRepository blogUsersRepository;

    public UserDetailsServiceImpl(BlogUsersRepository blogUsersRepository) {
        this.blogUsersRepository = blogUsersRepository;
    }

    /**
     * @param identifier needs to find user in our Repository
     * @return converted into SecurityUser blogUser
     * @throw UsernameNotFoundException
     */

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        BlogUser blogUser = null;
        try {
            blogUser = blogUsersRepository.findByEmail(identifier).orElse(blogUsersRepository.findByUsername(identifier));
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
        }
        return SecurityUser.fromUser(blogUser);

    }
}
