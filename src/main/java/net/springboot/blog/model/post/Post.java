package net.springboot.blog.model.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.springboot.blog.model.user.BlogUser;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long postId;

    @Column(name = "title")
    String title;

    @Column(name = "theme")
    String themeOfPost;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category", nullable = false)
    Category category;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_id", nullable = false)
    BlogUser blogUser;

    public Post(BlogUser blogUser, String title, String themeOfPost) {
        this.blogUser = blogUser;
        this.title = title;
        this.themeOfPost = themeOfPost;
    }

}
