package net.atoiebai.blog.exception.site.exception;


public class NoSuchUserException extends RuntimeException  {

    public NoSuchUserException(String message) {
        super(message);
    }

    public NoSuchUserException(String message, Throwable cause) {
        super(message, cause);
    }

}
