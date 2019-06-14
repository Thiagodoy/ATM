package br.com.agencialove.tpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import br.com.agencialove.tpa.dao.EmbalagemDao;
import br.com.agencialove.tpa.model.Embalagem;
import br.com.agencialove.tpa.model.Encomenda;
import br.com.agencialove.tpa.utils.HibernateUtils;
import br.com.agencialove.tpa.utils.Status;


 
public class TesteHibernate
{
   public static void main(String[] args)
   {
//      Session session = HibernateUtils.getSessionFactory().openSession();
//      session.beginTransaction();
//      // Add new Employee object
//      Encomenda emp = new Encomenda();
//      emp.setDestinatarioNome("Felipe Godoy");
//      session.save(emp);
//      
//      Encomenda empq = new Encomenda();
//      empq.setDestinatarioNome("Sabino Godoy");
//      session.save(empq);
//      
//      
//     
//      
//      Encomenda emp3 = new Encomenda();
//      emp3.setDestinatarioNome("Ana Godoy");
//      session.save(emp3);
//      
//      Encomenda empq4 = new Encomenda();
//      empq4.setDestinatarioNome("Gustavo Godoy");
//      session.save(empq4);
//      
//      session.getTransaction().commit();
//      HibernateUtils.shutdown();
	   
	   LocalDateTime min =  LocalDate.now().atStartOfDay();
	   LocalDateTime max = LocalDateTime.of(LocalDate.now(), LocalTime.NOON);
	   
	   
	 Session session = HibernateUtils.getSessionFactory().openSession();
     session.beginTransaction();

     Embalagem emp = new Embalagem();
     emp.setCpfCnpj(123l);
     emp.setEmail("email");
     emp.setNomeComprador("thiago");
     emp.setDataTransacao(LocalDateTime.now());
     session.save(emp);
     session.getTransaction().commit();
	   
	   
	  List<Embalagem>liest = EmbalagemDao.list(min, max, Status.NO_WRITED);
	  
	  for (Embalagem embalagem : liest) {
		System.out.println(embalagem);
	}
	   
	   
   }
}
