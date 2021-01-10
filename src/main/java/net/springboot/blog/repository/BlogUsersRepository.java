package net.springboot.blog.repository;

import net.springboot.blog.model.user.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogUsersRepository extends JpaRepository<BlogUser, Long> {
    Optional<BlogUser> findByEmail(String email);

    BlogUser findByUsername(String username);
}
