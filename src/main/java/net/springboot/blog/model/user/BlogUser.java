package net.springboot.blog.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class BlogUser implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @NotEmpty(message = "Name field can't be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "first_name", nullable = false)
    String firstName;

    @NotEmpty(message = "Surname field can't be empty")
    @Size(min = 2, max = 30, message = "Surname should be between 2 and 30 characters")
    @Column(name = "last_name", nullable = false)
    String lastName;

    @NotEmpty(message = "Email can't be empty")
    @Email(message = "Invalid email address")
    @Column(name = "email", nullable = false)
    String email;

    @NotEmpty(message = "username should not be empty")
    @Column(name = "username")
    String username;

    @Column(name = "sex")
    Sex sex;

    @NotEmpty(message = "Password can't be empty")
    @Column(name = "password", nullable = false)
    String password;

    @NotEmpty
    String confirmationPassword;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    Status status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return status.equals(Status.ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return status.equals(Status.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status.equals(Status.ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return status.equals(Status.ACTIVE);
    }

    public boolean checkPassword() {
     return confirmationPassword.equals(password);
    }
}
