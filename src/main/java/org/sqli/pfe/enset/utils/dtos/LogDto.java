package org.sqli.pfe.enset.utils.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private String body;
    private String correlation;
    private String url;
    private BodyInfosDto bodyInfosDto;
}
