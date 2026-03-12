package users.infra.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import users.infra.exceptions.ConflictException;
import users.infra.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorDto>> methodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        List<ValidationErrorDto> errorDtoList = new ArrayList<>();

        e.getFieldErrors()
                .forEach(error -> {
                    ValidationErrorDto errorDto = new ValidationErrorDto(
                            error.getField(), error.getDefaultMessage()
                    );

                    errorDtoList.add(errorDto);
                });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDtoList);
    }


    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ValidationErrorDto> conflictException(ConflictException e) {

        ValidationErrorDto errorDto = new ValidationErrorDto(null, e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDto);

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ValidationErrorDto> notFoundException(NotFoundException e) {

        ValidationErrorDto errorDto = new ValidationErrorDto(null, e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    // Captura erros genéricos
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DefaultErrorDto> genericException(Exception ex) {

        DefaultErrorDto defaultErrorDto = new DefaultErrorDto(
                "Internal server error",
                "An unexpected error occurred"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(defaultErrorDto);

    }

}
