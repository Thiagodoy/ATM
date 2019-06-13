package br.com.agencialove.tpa.jobs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.agencialove.tpa.dao.EmbalagemDao;
import br.com.agencialove.tpa.model.Embalagem;
import br.com.agencialove.tpa.utils.Status;

public class DailyJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {


		LocalDateTime min =  LocalDate.now().minusDays(1).atStartOfDay();
		LocalDateTime max = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.NOON);
		
		List<Embalagem>embalagens =  EmbalagemDao.list(min, max);		
		//Gravar no csv
		
		embalagens.parallelStream().forEach(e->{
			e.setStatus(Status.WRITED);
		});
		
		
		//Altera o status
		EmbalagemDao.save(embalagens);
		
		
	}

}
