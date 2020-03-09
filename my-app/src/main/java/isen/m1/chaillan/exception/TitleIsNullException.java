package isen.m1.chaillan.exception;

/**
 * TitleIsNull
 */
public class TitleIsNullException extends BookAttributeFormatException {

    public TitleIsNullException() {
    }

    public TitleIsNullException(String s) {
        super(s);
    }

    public TitleIsNullException(Throwable cause) {
        super(cause);
    }

    public TitleIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

}