package org.sqli.pfe.enset.batch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sqli.pfe.enset.models.entities.LogEntity;
import org.sqli.pfe.enset.repositories.LogRepository;
import org.sqli.pfe.enset.utils.enums.LogMethodEnum;
import org.sqli.pfe.enset.utils.enums.LogPathEnum;
import org.sqli.pfe.enset.utils.json.JsonUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LogWriter implements ItemWriter<LogEntity> {

    @Autowired
    private LogRepository logRepository;

    @Override
    public void write(List<? extends LogEntity> logs) throws Exception {
        List<? extends LogEntity> logsFiltred = logs.stream()
                .filter(logEntity -> LogMethodEnum.isELigibleMethod(
                        JsonUtils.getNodeValueAtPath(logEntity.getBody(), LogPathEnum.METHOD.getValue())))
                .collect(Collectors.toList());
        this.logRepository.saveAll(logsFiltred);
    }
}
