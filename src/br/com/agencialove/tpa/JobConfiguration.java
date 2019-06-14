package br.com.agencialove.tpa;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import br.com.agencialove.tpa.jobs.EmbalagemDailyJob;
import br.com.agencialove.tpa.jobs.EmbalagemMonthlyJob;

public class JobConfiguration {

	public static void start() {

		SchedulerFactory shedFact = new StdSchedulerFactory();
		try {
			Scheduler scheduler = shedFact.getScheduler();
			
			scheduler.start();

			JobDetail detail = JobBuilder.newJob(EmbalagemMonthlyJob.class).withIdentity("WRITE-JOB-", "process-file")
					.withDescription("Processing files").build();

			SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger-", "process-file")
					.startAt(new Date()).withSchedule(simpleSchedule()).build();
			
			scheduler.scheduleJob(detail, trigger);

		} catch (SchedulerException e) {			
			e.printStackTrace();
		}
	}

}
