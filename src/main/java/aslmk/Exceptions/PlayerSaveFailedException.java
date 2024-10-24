package aslmk.Exceptions;

public class PlayerSaveFailedException extends RuntimeException {
    public PlayerSaveFailedException(String message) {
        super(message);
    }
}
