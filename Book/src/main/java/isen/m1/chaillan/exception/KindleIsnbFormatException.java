package isen.m1.chaillan.exception;

public class KindleIsnbFormatException extends BookAttributeFormatException{

    public KindleIsnbFormatException() {
    }

    public KindleIsnbFormatException(String s) {
        super(s);
    }

    public KindleIsnbFormatException(Throwable cause) {
        super(cause);
    }

    public KindleIsnbFormatException(String message, Throwable cause) {
        super(message, cause);
    }

}
