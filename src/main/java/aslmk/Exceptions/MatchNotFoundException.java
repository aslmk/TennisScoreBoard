package aslmk.Exceptions;

public class MatchNotFoundException extends RuntimeException {
    public MatchNotFoundException() {
        super("Match not found");
    }
}
