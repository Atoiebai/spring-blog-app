package net.atoiebai.blog.exception.site.handler;

import com.sun.xml.bind.v2.TODO;
import net.atoiebai.blog.exception.site.exception.NoSuchUserException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;



@ControllerAdvice()
public class BlogExceptionHandler {
    // TODO: 2/4/2021 Logging and catching exceptions  
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String accessDenied() {
        
        return "exception/access-denied";
    }

    @ExceptionHandler(NoSuchUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFound() {
        
        return "exception/test-handle-template";
    }
}
