package hospedeAPI.example.hospedeAPI.exceptions;

public class InvalidCPFException extends RuntimeException{
    public InvalidCPFException(String message) {
        super(message);
    }
}
