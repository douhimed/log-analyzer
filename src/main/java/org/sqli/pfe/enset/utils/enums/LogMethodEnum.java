package org.sqli.pfe.enset.utils.enums;

import java.util.Arrays;

public enum LogMethodEnum {

    GET, POST, DELETE, PUT, PATCH;

    public static boolean isELigibleMethod(String value) {
        return Arrays.stream(LogMethodEnum.values())
                .anyMatch(method -> method.name().equalsIgnoreCase(value));
    }

}
