package com.appsbybirbeck.winecritic.api.exceptions;

abstract class WinecriticException extends Exception {

    WinecriticException(final String message) {
        super(message);
    }

    WinecriticException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
