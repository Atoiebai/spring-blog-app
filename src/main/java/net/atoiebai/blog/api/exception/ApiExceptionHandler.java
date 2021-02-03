package net.atoiebai.blog.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

     @ExceptionHandler(NoSuchUserException.class)
    public ResponseEntity<Object> handleApiException(NoSuchUserException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException exception = new ApiException(
                e.getMessage(),
                status,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(exception, status);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> handleResourceNotFoundException(Throwable e) {
        return new ResponseEntity<>(e , HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNotFoundException(Throwable e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiException exception = new ApiException(
                e.getMessage(),
                status,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(exception, status);
    }



}
