package net.atoiebai.blog.exception.api.exception;

// TODO: 2/4/2021 custom exception for substituting and handling api's behavior
public class ApiRequestException extends RuntimeException{

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
