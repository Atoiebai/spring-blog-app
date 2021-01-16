package net.atoiebai.blog.model.post;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.atoiebai.blog.model.user.BlogUser;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "posts")
@Getter
@Setter
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long postId;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    BlogUser blogUser;

    @Column(name = "title")
    @NotEmpty
    String title;

    @Column(name = "theme")
    String themeOfPost;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category", nullable = false)
    Category category;

//    @Column(nullable = false)
//    String slug;
//
//    @Column(nullable = false)
//    Date createdAt = new Date();
//
//    @Column(nullable = false)
//    boolean archived;
//
//    @Column(nullable = false)
//    boolean drafted;

}
