package org.sqli.pfe.enset.batch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sqli.pfe.enset.models.entities.LogEntity;
import org.sqli.pfe.enset.repositories.LogRepository;

import java.util.List;

@Component
public class LogWriter implements ItemWriter<LogEntity> {

    @Autowired
    private LogRepository logRepository;

    @Override
    public void write(List<? extends LogEntity> logs) throws Exception {
        this.logRepository.saveAll(logs);
    }
}
