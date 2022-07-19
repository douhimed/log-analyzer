package org.sqli.pfe.enset.utils.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.sqli.pfe.enset.utils.enums.ResponseStatutEnum;

@Builder
@Getter
@Setter
public class BodyInfosDto {

    private int statusValue;
    private ResponseStatutEnum responseStatutEnum;

}
