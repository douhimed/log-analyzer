package org.sqli.pfe.enset.utils.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.sqli.pfe.enset.utils.enums.ResponseStatutEnum;

@Builder
@Getter
@Setter
public class BodyInfosDto {

    private String statut;
    @JsonIgnore
    private ResponseStatutEnum statutEnum;
    private String method;
    private String userName;
}
