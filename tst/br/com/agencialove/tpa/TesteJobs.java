package br.com.agencialove.tpa;

import java.time.LocalDateTime;

import org.hibernate.Session;

import br.com.agencialove.tpa.model.Embalagem;
import br.com.agencialove.tpa.utils.HibernateUtils;
import br.com.agencialove.tpa.utils.Status;

public class TesteJobs {

	public static void main(String[] args) {

		FolderConfiguration.config();
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();

		for (int i = 0; i < 1; i++) {

			Embalagem emp = new Embalagem();
			emp.setNomeAgencia("Correios Araraquara");
			emp.setBandeira("" + 12);
			emp.setDisponibilidadeAtm(10.1d);
			emp.setEmail("Felipe.com" + i);			
			emp.setCpfCnpj(1232342342424l);
			emp.setEndereco("Avenia Uchoa 393 araraquara");
			emp.setModeloAtm("JPS ATM");
			emp.setNomeComprador("Thiasdfgo" + i);
			emp.setStatus(Status.NO_WRITED.name());
			emp.setDataTransacao(LocalDateTime.now().minusDays(1));
			session.save(emp);
			
		}

		session.getTransaction().commit();
		
		
		JobConfiguration.config();

	}

}
