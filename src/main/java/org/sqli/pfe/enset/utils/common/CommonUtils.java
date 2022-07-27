package org.sqli.pfe.enset.utils.common;

import java.util.Objects;

public final class CommonUtils {

    public static int from(String value) {
        return Integer.parseInt(value);
    }

    public static boolean isBlank(String value) {
        return Objects.isNull(value) || value.trim().length() == 0;
    }

    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }
}
