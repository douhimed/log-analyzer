package org.sqli.pfe.enset.utils.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.batch.core.BatchStatus;

import java.util.Date;

@Builder
@Getter
@Setter
public class BatchResponseDto {
    private BatchStatus status;
    private Date start;
    private Date end;
}
