package net.atoiebai.blog.exception.site.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
public class BlogException {
    private final String message;
    private final HttpStatus status;
    private final ZonedDateTime dateTime;
}
