package net.atoiebai.blog.security;

import lombok.AllArgsConstructor;
import net.atoiebai.blog.model.user.BlogUser;
import net.atoiebai.blog.model.user.Role;
import net.atoiebai.blog.model.user.Status;
import net.atoiebai.blog.model.token.ConfirmationToken;
import net.atoiebai.blog.service.registration.ConfirmationTokenService;
import net.atoiebai.blog.repository.BlogUsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final BlogUsersRepository blogUsersRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private final PasswordEncoder bCryptPasswordEncoder;



    /**
     * @param identifier needs to find user in our Repository
     * @return converted into SecurityUser blogUser
     * @throw UsernameNotFoundException
     */

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        return blogUsersRepository.findByEmail(identifier)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG, identifier)));

    }

    public String signUpUser(BlogUser user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);

        blogUsersRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                user
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }
}
