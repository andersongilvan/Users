package users.infra.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{
    private final int statusCode;

    public NotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }
}
