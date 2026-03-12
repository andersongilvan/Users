package users.infra.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

}
