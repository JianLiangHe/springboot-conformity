package edu.conformity.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job/")
public class BatchController {

	@Autowired
	private Job singleStepJob;
	
	@Autowired
	private Job multiBoundStepsJob;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@RequestMapping(value = "step", method = RequestMethod.GET)
	public String invokeStep() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
	                .toJobParameters();
		
        jobLauncher.run(singleStepJob, jobParameters);
		
		return "The job is proceed.";
	} 
	
	@RequestMapping(value = "steps", method = RequestMethod.POST)
	public String invokeSteps() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
					.toJobParameters();
		
		jobLauncher.run(multiBoundStepsJob, jobParameters);
		
		return "The multi bound steps job is proceed.";
	}
	
}
