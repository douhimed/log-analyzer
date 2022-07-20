package org.sqli.pfe.enset.utils.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDto {
    private long id;
    private String date;
    private String thread;
    private String login;
    private String body;
    private BodyInfosDto bodyInfosDto;

}
