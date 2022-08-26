package cybersoft.java18.backend.guessnumber.exception;

public class DatabaseNotFoundException extends RuntimeException{
    public DatabaseNotFoundException(String message) {
        super(message);
    }

    public DatabaseNotFoundException() {
        super();
    }
}
