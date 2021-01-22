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

    Optional<BlogUser> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE BlogUser a " +
            "SET a.status = 'ACTIVE' , a.role= 'USER' WHERE a.email = ?1")
    void enableBlogUser(String email);

    @Query("SELECT COUNT(x) FROM Post x WHERE x.blogUser.id = ?1")
    Integer countPostsOfUser(Long id);

}
