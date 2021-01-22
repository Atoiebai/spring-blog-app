package net.atoiebai.blog.model.post;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String themes;

    @Column(nullable = false)
    String slug;

    @Override
    public String toString() {
        return title;
    }

}
