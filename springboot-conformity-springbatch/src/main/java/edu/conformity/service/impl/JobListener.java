package edu.conformity.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Service;

@Service
public class JobListener implements JobExecutionListener {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void afterJob(JobExecution arg0) {
		logger.info("JOB IS STARTED. ");
	}

	@Override
	public void beforeJob(JobExecution arg0) {
		if (arg0.getStatus() == BatchStatus.FAILED) {
			logger.info("JOB IS EXECUTED FAILED. ");
		}
		if (arg0.getStatus() == BatchStatus.COMPLETED) {
			logger.info("JOB IS EXECUTED SUCCESSFULLY. ");
		}
	}

}
