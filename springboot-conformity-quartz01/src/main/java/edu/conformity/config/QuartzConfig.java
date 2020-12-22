package edu.conformity.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.conformity.job.DateTimeJob;

@Configuration
public class QuartzConfig {

	@Bean
	public JobDetail printTimeJobDetail() {
		return JobBuilder.newJob(DateTimeJob.class)
				.withIdentity("DateTimeJob")
				.usingJobData("msg", "hello quartz")
				.storeDurably()
				.build();
	}
	
	@Bean
	public Trigger printTimeJobTrigger() {
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");
		return TriggerBuilder.newTrigger()
				.forJob(printTimeJobDetail())
				.withIdentity("quartzTaskService")
				.withSchedule(cronScheduleBuilder)
				.build();
	}
	
}
