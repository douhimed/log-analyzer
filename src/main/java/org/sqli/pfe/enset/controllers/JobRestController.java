package org.sqli.pfe.enset.controllers;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sqli.pfe.enset.utils.dtos.BatchResponseDto;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/jobs")
public class JobRestController {

    public static final String MESSAGE_RUNNING = "BATCH RUNNING...";
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    @GetMapping("/importlogs")
    public ResponseEntity<BatchResponseDto> startImportLogs() throws Exception {
        final Map<String, JobParameter> params = new HashMap<>();
        params.put("", new JobParameter(System.currentTimeMillis()));
        final JobExecution jobExecution = jobLauncher.run(job, new JobParameters(params));
        while (jobExecution.isRunning()) {
            System.out.println(MESSAGE_RUNNING);
        }
        return ResponseEntity.ok(BatchResponseDto.builder()
                .status(jobExecution.getStatus())
                .start(jobExecution.getStartTime())
                .end(jobExecution.getEndTime())
                .build());
    }

}
