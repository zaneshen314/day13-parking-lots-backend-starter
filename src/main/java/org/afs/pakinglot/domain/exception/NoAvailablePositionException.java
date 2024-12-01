package org.afs.pakinglot.domain.exception;

public class NoAvailablePositionException extends RuntimeException {
    public NoAvailablePositionException() {
        super("No available position.");
    }
}
