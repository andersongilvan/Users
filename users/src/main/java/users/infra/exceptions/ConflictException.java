package users.infra.exceptions;

import lombok.Getter;

@Getter
public class ConflictException extends RuntimeException {

    private final int statusCode;

    public ConflictException(String message) {
        super(message);

        this.statusCode = 400;
    }
}
