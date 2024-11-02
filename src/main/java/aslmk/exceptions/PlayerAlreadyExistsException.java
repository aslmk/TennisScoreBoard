package aslmk.exceptions;

public class PlayerAlreadyExistsException extends RuntimeException {
    public PlayerAlreadyExistsException() {
        super("Player already exists");
    }
}
