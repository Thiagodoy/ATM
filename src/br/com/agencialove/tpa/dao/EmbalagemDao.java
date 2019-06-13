package br.com.agencialove.tpa.dao;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Query;

import org.hibernate.Session;

import br.com.agencialove.tpa.model.Embalagem;
import br.com.agencialove.tpa.utils.HibernateUtils;

public class EmbalagemDao {
	
	
	@SuppressWarnings("unchecked")
	public static List<Embalagem> list(LocalDateTime start, LocalDateTime end){
		
		Session session = HibernateUtils.getSessionFactory().openSession();	
		
		Query query = session.createQuery("select e from Embalagem e where e.dataTransacao between :start and :end");
		query.setParameter("start", start);
		query.setParameter("end", end);
		
		return  query.getResultList();		
	}
	
	
	public static void save(List<Embalagem> list){
		
		Session session = HibernateUtils.getSessionFactory().openSession();	
		session.getTransaction().begin();
		
		list.parallelStream().forEach(e->{
			session.save(e);
		});
		
		session.getTransaction().commit();		
	}
	

}
