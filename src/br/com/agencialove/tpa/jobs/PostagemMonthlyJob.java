package br.com.agencialove.tpa.jobs;

import java.io.File;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileDeleteStrategy;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.agencialove.tpa.dao.PostagemDao;
import br.com.agencialove.tpa.ftp.ClientFtpImpl;
import br.com.agencialove.tpa.ftp.IClientFtp;
import br.com.agencialove.tpa.model.Postagem;
import br.com.agencialove.tpa.utils.Status;
import br.com.agencialove.tpa.utils.Stream;
import br.com.agencialove.writer.BeanIoWriter;

public class PostagemMonthlyJob implements Job {

	private static final String DIR = System.getProperty("user.dir") + "\\upload\\encomenda\\";
	private static final String NAME_FILE = DIR + "{0}_BD_ATM_JPS_Mensal.txt";

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		try {

			LocalDateTime min = LocalDate.now().minusDays(1).with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
			LocalDateTime max = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MAX);

			List<Postagem> encomendas = PostagemDao.list(min, max, Status.WRITED);

			if (!encomendas.isEmpty()) {
				String dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd").format(min);
				String fileName = MessageFormat.format(NAME_FILE, dateFormat);
				File file = new File(fileName);
				BeanIoWriter.<Postagem>writer(encomendas, file, Stream.EMBALAGEM);				
			}

			IClientFtp ftp = new ClientFtpImpl();
			File uploadFolder = new File(DIR);
			
			List<File> files = Arrays.asList(uploadFolder.listFiles())
					.stream()
					.filter(f->f.getName().contains("BD_ATM_JPS_Mensal.txt"))
					.collect(Collectors.toList());		
			

			for (File file : files) {
				boolean isUploaded = ftp.uploadFile(file);
				if (isUploaded) {
					PostagemDao.deleteAll();
					FileDeleteStrategy.FORCE.delete(file);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
