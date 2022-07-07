package org.sqli.pfe.enset.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.sqli.pfe.enset.models.entities.LogEntity;
import org.sqli.pfe.enset.utils.dtos.LogDto;


@Configuration
@EnableBatchProcessing
public class LogAnalyserBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ItemWriter<LogEntity> logWriter;

    @Value("classpath:/json.log")
    Resource inputFile;

    @Autowired
    private ItemProcessor<LogDto, LogEntity> LogProcess;

    @Autowired
    private Tasklet dataBaseCleaningTasklet;

    @Bean
    public Job runJob() {
        return jobBuilderFactory
                .get("job-logs")
                .start(dataBaseCleaningStep())
                .next(getStepImportLogs())
                .build();
    }

    @Bean
    public Step dataBaseCleaningStep() {
        return stepBuilderFactory.get("cleaning-step")
                .tasklet(dataBaseCleaningTasklet)
                .build();
    }

    private Step getStepImportLogs() {
        return stepBuilderFactory
                .get("step-logs")
                .<LogDto, LogEntity>chunk(100)
                .reader(flatFileItemReader())
                .processor(LogProcess)
                .writer(logWriter)
                .build();
    }

    @Bean
    public FlatFileItemReader<LogDto> flatFileItemReader() {
        FlatFileItemReader<LogDto> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(inputFile);
        itemReader.setName("Log-Reader");
        itemReader.setLineMapper(lineMapper());
        return itemReader;

    }

    @Bean
    public LineMapper<LogDto> lineMapper() {
        DefaultLineMapper<LogDto> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(" - ");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("date", "thread", "login", "body");
        lineMapper.setLineTokenizer(lineTokenizer);
        BeanWrapperFieldSetMapper<LogDto> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(LogDto.class);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

}
