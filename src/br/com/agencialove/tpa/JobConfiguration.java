package br.com.agencialove.tpa;

import static org.quartz.CronScheduleBuilder.cronSchedule;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import br.com.agencialove.tpa.jobs.EmbalagemDailyJob;
import br.com.agencialove.tpa.jobs.EmbalagemMonthlyJob;
import br.com.agencialove.tpa.jobs.PostagemDailyJob;
import br.com.agencialove.tpa.jobs.PostagemMonthlyJob;

public class JobConfiguration {

	
	private static Properties properties;
	public static void config() {

		SchedulerFactory shedFact = new StdSchedulerFactory();
		try {
			loadProperties();
			
			Scheduler scheduler = shedFact.getScheduler();
			scheduler.start();

			jobEmbalagemMonthlyJob(scheduler);
			jobEncomendaMonthlyJob(scheduler);
			jobEncomendaDailyJob(scheduler);
			jobEmbalagemDailyJob(scheduler);

		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	private static void jobEmbalagemMonthlyJob(Scheduler scheduler) throws SchedulerException {

		String cron = properties.getProperty("job-embalagem-monthly");
		JobDetail detail = JobBuilder.newJob(EmbalagemMonthlyJob.class).withIdentity("EmbalagemMonthlyJob", "process-file")
				.withDescription("Processing files").build();
		CronTrigger crontrigger = TriggerBuilder.newTrigger().withIdentity("EmbalagemMonthlyJobTrigger", "process-file")
				.withSchedule(cronSchedule(cron).withMisfireHandlingInstructionFireAndProceed()).build();
		scheduler.scheduleJob(detail, crontrigger);

	}

	private static void jobEncomendaMonthlyJob(Scheduler scheduler) throws SchedulerException {
		String cron = properties.getProperty("job-encomenda-monthly");
		JobDetail detail = JobBuilder.newJob(PostagemMonthlyJob.class).withIdentity("EncomendaMonthlyJob", "process-file")
				.withDescription("Processing files").build();
		CronTrigger crontrigger = TriggerBuilder.newTrigger().withIdentity("EncomendaMonthlyJobTrigger", "process-file")
				.withSchedule(cronSchedule(cron).withMisfireHandlingInstructionFireAndProceed()).build();
		scheduler.scheduleJob(detail, crontrigger);

	}
	
	private static void jobEncomendaDailyJob(Scheduler scheduler) throws SchedulerException {
		String cron = properties.getProperty("job-encomenda-daily");
		JobDetail detail = JobBuilder.newJob(PostagemDailyJob.class).withIdentity("EncomendaDailyJob", "process-file")
				.withDescription("Processing files").build();
		CronTrigger crontrigger = TriggerBuilder.newTrigger().withIdentity("EncomendaDailyJobTrigger", "process-file")
				.withSchedule(cronSchedule(cron).withMisfireHandlingInstructionFireAndProceed()).build();
		scheduler.scheduleJob(detail, crontrigger);

	}
	
	private static void jobEmbalagemDailyJob(Scheduler scheduler) throws SchedulerException {

		String cron = properties.getProperty("job-embalagem-daily");
		JobDetail detail = JobBuilder.newJob(EmbalagemDailyJob.class).withIdentity("EmbalagemDailyJob", "process-file")
				.withDescription("Processing files").build();
		CronTrigger crontrigger = TriggerBuilder.newTrigger().withIdentity("EmbalagemDailyJobTrigger", "process-file")
				.withSchedule(cronSchedule(cron).withMisfireHandlingInstructionFireAndProceed()).build();
		scheduler.scheduleJob(detail, crontrigger);

	}
	
	
	private static void loadProperties() {

		try (InputStream input = new FileInputStream("quartz-job.properties")) {

			Properties prop = new Properties();
			prop.load(input);		

			properties =  prop;

		} catch (IOException ex) {
			ex.printStackTrace();
			properties =  null;
		}
	}

}
