package br.com.agencialove.tpa.dao;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Query;

import org.hibernate.Session;

import br.com.agencialove.tpa.model.Embalagem;
import br.com.agencialove.tpa.utils.HibernateUtils;
import br.com.agencialove.tpa.utils.Status;

public class EmbalagemDao {

	@SuppressWarnings("unchecked")
	public static List<Embalagem> list(LocalDateTime start, LocalDateTime end, Status status) {

		Session session = HibernateUtils.getSessionFactory().openSession();

		Query query = session.createQuery(
				"select e from Embalagem e where e.dataTransacao between :start and :end and e.status = :status");
		query.setParameter("start", start);
		query.setParameter("end", end);
		query.setParameter("status", status.name());

		return query.getResultList();
	}

	public static void save(List<Embalagem> list) {

		Session session = HibernateUtils.getSessionFactory().openSession();
		session.getTransaction().begin();

		list.forEach(e -> {
			session.saveOrUpdate(e);
		});

		session.flush();
		session.getTransaction().commit();

	}

	public static void save(Embalagem embalagem) {

		Session session = HibernateUtils.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.save(embalagem);		
		session.getTransaction().commit();

	}
	
	public static void deleteAll() {

		Session session = HibernateUtils.getSessionFactory().openSession();
		Query query = session.createQuery("delete from Embalagem e");
		query.executeUpdate();
		session.getTransaction().commit();

	}

}
