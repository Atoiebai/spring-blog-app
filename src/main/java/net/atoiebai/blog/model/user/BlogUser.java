package net.atoiebai.blog.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BlogUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name", nullable = false)
    @Size(min = 2, max = 30, message = "required field | обязательное поле (2-30 letters)")
    String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(min = 2, max = 30, message = "required field | обязательное поле (2-30 letters)")
    String lastName;

    @Email(message = "Invalid email address")
    @NotEmpty(message = "required field | обязательное поле")
    @Column(name = "email", nullable = false)
    String email;

    @Column(name = "username", nullable = false)
    @NotEmpty(message = "required field | обязательное поле")
    String username;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "sex", nullable = false)
    Sex sex;

    @Column(name = "bio")
    String bio;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "required field | обязательное поле")
    String password;

    @Transient
    String confirmationPassword;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    Status status;

    @CreationTimestamp
    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    public BlogUser(@Size(min = 2, max = 30, message = "required field | обязательное поле (2-30 letters)") String firstName,
                    @Size(min = 2, max = 30, message = "required field | обязательное поле (2-30 letters)") String lastName,
                    @NotEmpty(message = "required field | обязательное поле") String email,
                    @NotEmpty(message = "required field | обязательное поле") String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return status.equals(Status.ACTIVE) || status.equals(Status.UNCONFIRMED);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !status.equals(Status.BANNED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status.equals(Status.UNCONFIRMED) || status.equals(Status.ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return !status.equals(Status.BANNED);
    }

    public boolean checkPassword() {
        return confirmationPassword.equals(password);
    }
}
