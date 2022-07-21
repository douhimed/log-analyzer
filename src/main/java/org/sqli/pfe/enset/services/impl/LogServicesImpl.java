package org.sqli.pfe.enset.services.impl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.sqli.pfe.enset.repositories.LogRepository;
import org.sqli.pfe.enset.services.LogServices;
import org.sqli.pfe.enset.utils.dtos.BatchResponseDto;
import org.sqli.pfe.enset.utils.dtos.LogDto;
import org.sqli.pfe.enset.utils.exceptions.DataNotFoundException;
import org.sqli.pfe.enset.utils.mappers.LogMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class LogServicesImpl implements LogServices {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private LogRepository logRepository;

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

    @Override
    public Page<LogDto> findAll(Pageable pageable) {
        return this.logRepository.findAll(pageable)
                .map(LogMapper::from);
    }

    @Override
    public LogDto findById(long id) {
        return this.logRepository.findById(id)
                .map(LogMapper::from)
                .orElseThrow(() -> new DataNotFoundException("Log Not Found"));
    }
}