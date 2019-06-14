package br.com.agencialove.tpa.jobs;

import java.io.File;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileDeleteStrategy;
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

	private static final String DIR = System.getProperty("user.dir") + "\\upload\\embalagem\\";
	private static final String NAME_FILE = DIR + "{0}_BD_ATM_JPS_Embalagens.txt";

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		try {

			LocalDateTime min = LocalDate.now().minusDays(1).atStartOfDay();
			LocalDateTime max = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MAX);

			List<Embalagem> embalagens = EmbalagemDao.list(min, max, Status.NO_WRITED);

			if (!embalagens.isEmpty()) {

				String dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd").format(min);
				String fileName = MessageFormat.format(NAME_FILE, dateFormat);

				File file = new File(fileName);

				boolean writedFile = BeanIoWriter.<Embalagem>writer(embalagens, file, Stream.EMBALAGEM);

				if (writedFile) {
					embalagens.parallelStream().forEach(e -> {
						e.setStatus(Status.WRITED.name());
					});
					EmbalagemDao.save(embalagens);
				}
			}

			IClientFtp ftp = new ClientFtpImpl();
			File uploadFolder = new File(DIR);
			List<File> files = Arrays.asList(uploadFolder.listFiles())
					.stream()
					.filter(f->f.getName().contains("_BD_ATM_JPS_Embalagens.txt"))
					.collect(Collectors.toList());


			for (File file : files) {
				boolean isUploaded = ftp.uploadFile(file);
				if (isUploaded) {
					FileDeleteStrategy.FORCE.delete(file);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
