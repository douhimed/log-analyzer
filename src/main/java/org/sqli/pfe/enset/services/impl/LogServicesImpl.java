package org.sqli.pfe.enset.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sqli.pfe.enset.services.LogServices;
import org.sqli.pfe.enset.utils.dtos.BatchResponseDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class LogServicesImpl implements LogServices {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Override
    public BatchResponseDto runBatch() {
        final Map<String, JobParameter> params = new HashMap<>();
        params.put("", new JobParameter(System.currentTimeMillis()));
        JobExecution jobExecution = null;
        try {
            jobExecution = jobLauncher.run(job, new JobParameters(params));
        } catch (Exception e) {
            log.error("Error at Import Logs : " + e.getMessage());
        }
        return Objects.nonNull(jobExecution) ?
                BatchResponseDto.builder()
                        .status(jobExecution.getStatus())
                        .start(jobExecution.getStartTime())
                        .end(jobExecution.getEndTime())
                        .build()
                : BatchResponseDto.builder().status(BatchStatus.FAILED).build();
    }
}
