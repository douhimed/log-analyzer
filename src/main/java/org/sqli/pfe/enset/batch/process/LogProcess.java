package org.sqli.pfe.enset.batch.process;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.sqli.pfe.enset.models.entities.LogEntity;
import org.sqli.pfe.enset.utils.dtos.LogDto;
import org.sqli.pfe.enset.utils.mappers.LogMapper;

@Component
public class LogProcess implements ItemProcessor<LogDto, LogEntity> {

    @Override
    public LogEntity process(LogDto logDto) throws Exception {
        return LogMapper.from(logDto);
    }
}

