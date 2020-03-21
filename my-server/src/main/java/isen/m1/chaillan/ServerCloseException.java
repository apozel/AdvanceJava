package isen.m1.chaillan;

/**
 * ServerCloseException
 */
public class ServerCloseException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 8698433015797658301L;

    public ServerCloseException() {
    }

    public ServerCloseException(String message) {
        super(message);
    }

    public ServerCloseException(Throwable cause) {
        super(cause);
    }

    public ServerCloseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerCloseException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    
}