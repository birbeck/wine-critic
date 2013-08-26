package com.appsbybirbeck.winecritic.api.exceptions;

public class WineNotFoundException extends WinecriticException {

    public WineNotFoundException(final String message) {
        super(message);
    }

    public WineNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
