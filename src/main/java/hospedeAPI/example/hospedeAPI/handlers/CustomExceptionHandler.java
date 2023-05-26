package hospedeAPI.example.hospedeAPI.handlers;

import hospedeAPI.example.hospedeAPI.exceptions.HospedeExistentingException;
import hospedeAPI.example.hospedeAPI.exceptions.InvalidCPFException;
import hospedeAPI.example.hospedeAPI.exceptions.InvalidDateException;
import hospedeAPI.example.hospedeAPI.exceptions.InvalidTelefoneFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(InvalidCPFException.class)
    public ResponseEntity<String> handleInvalidCpfException(InvalidCPFException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HospedeExistentingException.class)
    public ResponseEntity<String> handleHospedeExistentingException(HospedeExistentingException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<String> handleInvalidDateException(InvalidDateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTelefoneFormatException.class)
    public ResponseEntity<String> handleInvalidTelefoneFormatException(InvalidTelefoneFormatException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
