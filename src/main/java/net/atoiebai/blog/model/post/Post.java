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
    @Column(name = "id")
    Long id;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    BlogUser blogUser;

    @Column(name = "title", nullable = false)
    @NotEmpty
    String title;

    @Column(name = "theme", nullable = false)
    String theme;

    @Column(name = "body", nullable = false)
    String body;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @Column(nullable = false)
    String slug;

    @Column(nullable = false)
    Date createdAt = new Date();

    @Column(nullable = false)
    boolean archived = false;

    @Column(nullable = false)
    boolean drafted = true;

}
