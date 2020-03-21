package isen.m1.chaillan.exception;

/**
 * TitleIsEmptyException
 */
public class TitleIsEmptyException extends BookAttributeFormatException {

    public TitleIsEmptyException() {
    }

    public TitleIsEmptyException(String s) {
        super(s);
    }

    public TitleIsEmptyException(Throwable cause) {
        super(cause);
    }

    public TitleIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

}