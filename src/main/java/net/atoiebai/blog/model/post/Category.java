package net.atoiebai.blog.model.post;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// TODO: 2/4/2021 comments 
@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    String themes;

    @Column(nullable = false, columnDefinition = "TEXT")
    String slug;

    @Override
    public String toString() {
        return title;
    }

}
