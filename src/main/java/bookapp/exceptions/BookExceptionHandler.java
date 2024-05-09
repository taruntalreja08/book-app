package bookapp.exceptions;

import bookapp.dto.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookExceptionHandler {
    @ExceptionHandler(value = BookIdExistException.class)
    public ResponseEntity<GenericResponse> bookIdExists(BookIdExistException ex) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(genericResponse, HttpStatus.FOUND);
    }

    @ExceptionHandler(value = BookIdNotExistException.class)
    public ResponseEntity<GenericResponse> bookIdNotExists(BookIdNotExistException ex) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
    }
}
