package bu.eugene.map.exception.handler;

import bu.eugene.map.exception.PasswordDoesntMatchException;
import bu.eugene.map.exception.UsernameIsTakenException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProjectErrorHandler {

    @ExceptionHandler(UsernameIsTakenException.class)
    public ResponseEntity<?> usernameIsTakenHandler(UsernameIsTakenException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundExceptionHandler(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordDoesntMatchException.class)
    public ResponseEntity<?> passwordDoesntMatchExceptionHandler(PasswordDoesntMatchException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
