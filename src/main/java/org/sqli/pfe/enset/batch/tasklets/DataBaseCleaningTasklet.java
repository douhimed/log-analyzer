package org.sqli.pfe.enset.batch.tasklets;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sqli.pfe.enset.repositories.LogRepository;

@Component
public class DataBaseCleaningTasklet implements Tasklet {

    @Autowired
    private LogRepository logRepository;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        this.logRepository.deleteAllInBatch();
        return RepeatStatus.FINISHED;
    }
}
