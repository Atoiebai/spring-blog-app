package net.atoiebai.blog.security;

import net.atoiebai.blog.model.user.BlogUser;
import net.atoiebai.blog.repository.BlogUsersRepository;
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
        BlogUser user = null;
        try {
            user = blogUsersRepository.findByEmail(identifier);
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }
}
