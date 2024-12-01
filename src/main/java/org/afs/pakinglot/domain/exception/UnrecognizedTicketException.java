package org.afs.pakinglot.domain.exception;

public class UnrecognizedTicketException extends RuntimeException {
    public UnrecognizedTicketException() {
        super("Unrecognized parking ticket.");
    }
}
