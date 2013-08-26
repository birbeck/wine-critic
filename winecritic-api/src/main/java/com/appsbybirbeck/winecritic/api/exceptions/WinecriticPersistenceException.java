package com.appsbybirbeck.winecritic.api.exceptions;

public class WinecriticPersistenceException extends WinecriticException {

    public WinecriticPersistenceException(final String message) {
        super(message);
    }

    public WinecriticPersistenceException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
