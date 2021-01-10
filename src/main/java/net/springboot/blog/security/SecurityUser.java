package net.springboot.blog.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.springboot.blog.model.user.BlogUser;
import net.springboot.blog.model.user.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }


    /**
     * Method takes a user and converts it to UserDetails
     *
     * @param blogUser is a user from database
     * @return new Spring Security user
     */
    public static UserDetails fromUser(BlogUser blogUser) {

        return new User(blogUser.getEmail(),
                blogUser.getPassword(),
                blogUser.getStatus().equals(Status.ACTIVE),
                blogUser.getStatus().equals(Status.ACTIVE),
                blogUser.getStatus().equals(Status.ACTIVE),
                blogUser.getStatus().equals(Status.ACTIVE),
                blogUser.getRole().getAuthorities()
        );
    }
}
