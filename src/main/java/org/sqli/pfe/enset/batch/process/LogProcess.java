package org.sqli.pfe.enset.batch.process;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.sqli.pfe.enset.models.entities.LogEntity;
import org.sqli.pfe.enset.utils.dtos.LogDto;

import java.text.SimpleDateFormat;

@Component
public class LogProcess implements ItemProcessor<LogDto, LogEntity> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

    @Override
    public LogEntity process(LogDto logDto) throws Exception {
        return LogEntity.builder()
                .thread(logDto.getThread())
                .date(dateFormat.parse(logDto.getDate()))
                .login(logDto.getLogin())
                .request(logDto.getRequest())
                .build();
    }
}
