package net.atoiebai.blog.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    Long id;

    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    @Size(min = 2, max = 30, message = "required field | обязательное поле (2-30 letters)")
    String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    @Size(min = 2, max = 30, message = "required field | обязательное поле (2-30 letters)")
    String lastName;

    @Email(message = "Invalid email address")
    @NotEmpty(message = "required field | обязательное поле")
    @Column(name = "email", nullable = false, unique = true, columnDefinition = "TEXT")
    String email;

    @Column(name = "username", nullable = false, unique = true, columnDefinition = "TEXT")
    @NotEmpty(message = "required field | обязательное поле")
    String username;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "sex", nullable = false, updatable = false)
    Sex sex;

    @Column(name = "bio", columnDefinition = "TEXT")
    String bio;

    @Column(nullable = false, unique = true)
    String slug;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    @NotEmpty(message = "required field | обязательное поле")
    String password;

    @Transient
    String confirmationPassword;

    @JsonIgnore
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    Role role;

    @JsonIgnore
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    Status status;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false, columnDefinition = "DATE")
    LocalDateTime created;

    public BlogUser(@Size(min = 2, max = 30, message = "required field | обязательное поле (2-30 letters)") String firstName,
                    @Size(min = 2, max = 30, message = "required field | обязательное поле (2-30 letters)") String lastName,
                    @NotEmpty(message = "required field | обязательное поле") String email,
                    @NotEmpty(message = "required field | обязательное поле") String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return status.equals(Status.ACTIVE) || status.equals(Status.UNCONFIRMED);
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return !status.equals(Status.BANNED);
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return status.equals(Status.UNCONFIRMED) || status.equals(Status.ACTIVE);
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return !status.equals(Status.BANNED);
    }

    @JsonIgnore
    public boolean checkPassword() {
        return confirmationPassword.equals(password);
    }
}
