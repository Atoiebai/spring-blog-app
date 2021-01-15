package net.atoiebai.blog.repository;

import net.atoiebai.blog.model.user.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogUsersRepository extends JpaRepository<BlogUser, Long> {

    BlogUser findByEmail(String email);

}
