package edu.pro.batching.listener;
/*
  @author   george
  @project   batching
  @class  JobNotificationListener
  @version  1.0.0 
  @since 18.04.22 - 12.20
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class JobNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log =
            LoggerFactory.getLogger(JobNotificationListener.class);

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

        }
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.STARTED) {
            log.info("!!! JOB STARTED! ");

        }
    }


}
