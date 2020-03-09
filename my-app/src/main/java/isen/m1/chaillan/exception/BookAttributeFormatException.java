package isen.m1.chaillan.exception;

public class BookAttributeFormatException extends IllegalArgumentException{

    public BookAttributeFormatException() {
    }

    public BookAttributeFormatException(String s) {
        super(s);
    }

    public BookAttributeFormatException(Throwable cause) {
        super(cause);
    }

    public BookAttributeFormatException(String message, Throwable cause) {
        super(message, cause);
    }

}
