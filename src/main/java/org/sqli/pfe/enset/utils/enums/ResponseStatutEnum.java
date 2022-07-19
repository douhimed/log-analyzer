package org.sqli.pfe.enset.utils.enums;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.sqli.pfe.enset.utils.CommonUtils;

@Getter
@Slf4j
public enum ResponseStatutEnum {

    SUCCESS("success"),
    ERREUR_SERVEUR("danger"),
    ERREUR_CLIENT("warning"),
    REDIRECTION("info"),
    UNKNOWN("dark");

    private final String value;

    ResponseStatutEnum(String value) {
        this.value = value;
    }

    public static ResponseStatutEnum fromStatus(String statut) {

        try {
            final int currentStatut = CommonUtils.from(statut);
            if (currentStatut >= 200 && currentStatut < 300)
                return SUCCESS;
            if (currentStatut >= 300 && currentStatut < 400)
                return REDIRECTION;
            if (currentStatut >= 400 && currentStatut < 500)
                return ERREUR_CLIENT;
            if (currentStatut >= 500)
                return ERREUR_SERVEUR;
            return UNKNOWN;
        } catch (Exception e) {
            log.warn("ResponseStatutEnum : " + e.getMessage());
            return UNKNOWN;
        }
    }

}
