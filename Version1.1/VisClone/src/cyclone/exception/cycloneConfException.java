package cyclone.exception;

/**
 * Created by DilshaniS on 30/06/2015.
 */
public class cycloneConfException extends Exception {
    /**
     * Exception send to upper level
     * @param message - Error message
     * @param cause - Error
     */
    public cycloneConfException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception send to upper level
     * @param message - Error message
     */
    public cycloneConfException(String message) {
        super(message);
    }
    /**
     * Exception send to upper level
     * @param cause - Error
     */
    public cycloneConfException(Throwable cause) {
        super(cause);
    }
}
