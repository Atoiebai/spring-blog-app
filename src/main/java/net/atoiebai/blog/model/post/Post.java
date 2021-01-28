package net.atoiebai.blog.model.post;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.atoiebai.blog.model.user.BlogUser;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "posts")
@Getter
@Setter
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    Long id;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    BlogUser blogUser;

    @Column(name = "title", nullable = false, columnDefinition = "TEXT")
    @NotEmpty
    String title;

    @Column(name = "theme", nullable = false, columnDefinition = "TEXT")
    String theme;

    @Column(name = "body", nullable = false, columnDefinition = "TEXT")
    String body;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", nullable = false, columnDefinition = "TEXT")
    Category category;

    @Column(nullable = false, unique = true)
    String slug;

    @Column(nullable = false, updatable = false, columnDefinition = "DATE")
    Date createdAt = new Date();

    @Column(nullable = false)
    boolean archived = false;

    @Column(nullable = false)
    boolean drafted = true;

}
