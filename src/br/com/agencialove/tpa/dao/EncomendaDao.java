package br.com.agencialove.tpa.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import br.com.agencialove.tpa.model.Encomenda;
import br.com.agencialove.tpa.utils.HibernateUtils;
import br.com.agencialove.tpa.utils.Status;

public class EncomendaDao {
	
	@SuppressWarnings("unchecked")
	public static List<Encomenda> list(LocalDateTime start, LocalDateTime end, Status status){
		
		Session session = HibernateUtils.getSessionFactory().openSession();	
		
		Query query = session.createQuery("select e from Encomenda e where e.dataTransacao between :start and :end and e.status = :status");
		query.setParameter("start", start);
		query.setParameter("end", end);
		query.setParameter("status", status.name());
		
		return  query.getResultList();		
	}
	
	
	public static void save(List<Encomenda> list){
		
		Session session = HibernateUtils.getSessionFactory().openSession();	
		session.getTransaction().begin();
		
		list.forEach(e->{
			session.saveOrUpdate(e);
		});
		
		session.flush();
		session.getTransaction().commit();	
		
	}
	

}
