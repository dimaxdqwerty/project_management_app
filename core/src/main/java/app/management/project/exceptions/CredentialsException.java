package app.management.project.exceptions;

public class CredentialsException extends Exception {

    private static final long serialVersionUID = 1L;

    public CredentialsException() {
        super();
    }

    public CredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CredentialsException(String message) {
        super(message);
    }

}
