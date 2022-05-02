package org.example.titanic.exception;

public class HeaderMissingException extends CSVException {
    public HeaderMissingException() {
    }

    public HeaderMissingException(final String message) {
        super(message);
    }

    public HeaderMissingException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
