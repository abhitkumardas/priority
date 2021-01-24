package in.tatsam.priority.exception;

public class CustomRuntimeException extends RuntimeException{
    private String errorMessage;
    public CustomRuntimeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
