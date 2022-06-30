package org.sqli.pfe.enset.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
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
import org.sqli.pfe.enset.batch.process.LogProcess;
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
    private ItemReader<LogDto> LogReader;
    @Autowired
    private ItemWriter<LogEntity> LogWriter;

    @Autowired private ItemProcessor<LogDto,LogEntity> LogProcess;

    @Bean
    public Job LogJob(){
        Step step1=stepBuilderFactory.get("step-load-data")
                .<LogDto,LogEntity>chunk(100)
                .reader(LogReader)
                .processor(LogProcess)
                .writer(LogWriter)
                .build();

        return jobBuilderFactory.get("Log-data-loader-job")
                .start(step1).build();
    }

    @Bean
    public FlatFileItemReader<LogDto> flatFileItemReader(@Value("${inputFile}") Resource inputFile) {
        FlatFileItemReader<LogDto> fileItemReader = new FlatFileItemReader<>();
        fileItemReader.setName("FlateFileItemReader1");
        fileItemReader.setLinesToSkip(1);
        fileItemReader.setResource(inputFile);
        fileItemReader.setLineMapper(lineMapper());

        return fileItemReader;
    }

    @Bean
    public LineMapper<LogDto> lineMapper() {
        DefaultLineMapper<LogDto> lineMapper=new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(" - ");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("date","thread","login","request");
        lineMapper.setLineTokenizer(lineTokenizer);
        BeanWrapperFieldSetMapper fieldSetMapper=new BeanWrapperFieldSetMapper();
        fieldSetMapper.setTargetType(LogDto.class);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }


}
