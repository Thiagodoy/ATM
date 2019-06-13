package br.com.agencialove.tpa.jobs;

import java.io.File;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.agencialove.tpa.dao.EmbalagemDao;
import br.com.agencialove.tpa.ftp.ClientFtpImpl;
import br.com.agencialove.tpa.ftp.IClientFtp;
import br.com.agencialove.tpa.model.Embalagem;
import br.com.agencialove.tpa.utils.Status;
import br.com.agencialove.tpa.utils.Stream;
import br.com.agencialove.writer.BeanIoWriter;

public class EmbalagemDailyJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {


		LocalDateTime min =  LocalDate.now().minusDays(1).atStartOfDay();
		LocalDateTime max = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MAX);
		
		List<Embalagem>embalagens =  EmbalagemDao.list(min, max);		

		String dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd").format(min);		
		String fileName = MessageFormat.format("{0}_BD_ATM_JPS.txt", dateFormat);
		
		Optional<File> opt = BeanIoWriter.<Embalagem>writer(embalagens, fileName, Stream.EMBALAGEM);
		
		if(opt.isPresent()) {
			
			embalagens.parallelStream().forEach(e->{
				e.setStatus(Status.WRITED.name());
			});
			
			EmbalagemDao.save(embalagens);		
			
			IClientFtp ftp = new ClientFtpImpl();			
			try {
				ftp.uploadFile(opt.get());
			} catch (Exception e1) {
				e1.printStackTrace();
			}		
			
		}
		
		
		
	
		
		
		
	}

}
