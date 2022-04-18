package edu.pro.batching.configuration;
/*
  @author   george
  @project   batching
  @class  BatchConfiguration
  @version  1.0.0 
  @since 18.04.22 - 12.26
*/

import edu.pro.batching.listener.JobNotificationListener;
import edu.pro.batching.model.Employee;
import edu.pro.batching.model.EmployeeDetail;
import edu.pro.batching.processor.EmployeeItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<EmployeeDetail> reader() {
        return new FlatFileItemReaderBuilder<EmployeeDetail>().name("EmployeeItemReader")
                .resource(new ClassPathResource("mock-data.csv")).delimited()
                .names(new String[] {"name", "email", "country", "team"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<EmployeeDetail>() {
                    {
                        setTargetType(EmployeeDetail.class);
                    }
                }).build();
    }

    @Bean
    public MongoItemWriter<Employee> writer(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<Employee>().template(mongoTemplate).collection("employee")
                .build();
    }

    @Bean
    public EmployeeItemProcessor processor() {
        return new EmployeeItemProcessor();
    }

    @Bean
    public Step step1(FlatFileItemReader<EmployeeDetail> itemReader, MongoItemWriter<Employee> itemWriter)
            throws Exception {
        return this.stepBuilderFactory.get("step1").<EmployeeDetail, Employee>chunk(10).reader(itemReader)
                .processor(processor()).writer(itemWriter).build();
    }

    @Bean
    public Job updateEmployeJob(JobNotificationListener listener, Step step1)
            throws Exception {

        return this.jobBuilderFactory.get("updateEmployeeJob").incrementer(new RunIdIncrementer())
                .listener(listener).start(step1).build();
    }


}
