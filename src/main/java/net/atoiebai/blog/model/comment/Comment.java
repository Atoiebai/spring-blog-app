package net.atoiebai.blog.model.comment;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String body;

    @Column(nullable = false)
    Date createdAt = new Date();
}
