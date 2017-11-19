package my.artfultom.wexapi.exception;

public class ReadOnlyException extends RuntimeException {

    public ReadOnlyException() {
        super("Key and secret don't exist! Read-only mode.");
    }
}
