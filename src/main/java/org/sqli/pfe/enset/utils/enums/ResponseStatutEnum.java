package org.sqli.pfe.enset.utils.enums;

import lombok.Getter;

@Getter
public enum ResponseStatutEnum {

    SUCCESS("success"),
    ERREUR_SERVEUR("danger"),
    ERREUR_CLIENT("warning"),
    REDIRECTION("info"),
    UNKNOWN("dark");

     private String value;

    ResponseStatutEnum(String value) {
        this.value = value;
    }

    public static ResponseStatutEnum fromStatus(int statut) {
        if(statut >=200 && statut < 300)
            return SUCCESS;
        if (statut >=300 && statut < 400)
            return REDIRECTION;
        if (statut >= 400 && statut < 500)
            return ERREUR_CLIENT;
        if(statut >=500)
            return ERREUR_SERVEUR;
        return UNKNOWN;
    }

}
