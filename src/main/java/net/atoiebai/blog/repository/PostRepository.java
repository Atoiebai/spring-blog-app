package net.atoiebai.blog.repository;

import net.atoiebai.blog.model.post.Category;
import net.atoiebai.blog.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.category = :category")
    List<Post> findAllByCategory(@Param("category") Category category);

}
