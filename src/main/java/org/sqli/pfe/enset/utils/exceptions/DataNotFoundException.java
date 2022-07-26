package org.sqli.pfe.enset.utils.exceptions;

import lombok.Getter;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }

    @Getter
    public enum DataNotFoundRaisons {
        ID_NOT_FOUND("Log with the given id {%s} not found"),
        PAGE_NOT_FOUND("Page with given number {%s} not exist");

        final String reason;

        DataNotFoundRaisons(String reason) {
            this.reason = reason;
        }
    }
}
