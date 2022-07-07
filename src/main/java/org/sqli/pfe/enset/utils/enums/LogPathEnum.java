package org.sqli.pfe.enset.utils.enums;

import lombok.Getter;

@Getter
public enum LogPathEnum {
    METHOD("/method"),
    STATUS("/status");

    String value;

    LogPathEnum(String value) {
        this.value = value;
    }

}
