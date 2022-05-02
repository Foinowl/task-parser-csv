package org.example.titanic.exception;

public class CSVException extends RuntimeException{

    public CSVException() {
    }

    public CSVException(final String message) {
        super(message);
    }

    public CSVException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CSVException(final Throwable cause) {
        super(cause);
    }

    public CSVException(final String message, final Throwable cause, final boolean enableSuppression,
                        final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
