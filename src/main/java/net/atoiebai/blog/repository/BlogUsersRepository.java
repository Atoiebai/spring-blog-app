package net.atoiebai.blog.repository;

import net.atoiebai.blog.model.user.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface BlogUsersRepository extends JpaRepository<BlogUser, Long> {

    Optional<BlogUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE BlogUser a " +
            "SET a.status = 'ACTIVE' WHERE a.email = ?1")
    void enableBlogUser(String email);

}
