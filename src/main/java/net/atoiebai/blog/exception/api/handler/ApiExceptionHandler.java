package net.atoiebai.blog.exception.api.handler;

import net.atoiebai.blog.exception.api.model.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice("net.atoiebai.blog.api.")
public class ApiExceptionHandler {

    @ExceptionHandler({NoSuchElementException.class , IllegalArgumentException.class})
    public ResponseEntity<Object> handleApiException(Exception e) {
        HttpStatus status;
        status = e instanceof NoSuchElementException ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
        ApiException exception = new ApiException(
                e.getMessage(),
                status,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(exception, status);
    }

}
