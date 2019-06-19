package br.com.agencialove.tpa.dao;

import org.hibernate.Session;

import br.com.agencialove.tpa.model.Agencia;
import br.com.agencialove.tpa.utils.HibernateUtils;

public class AgenciaDao {

	public static Agencia getAgencia() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		return session.get(Agencia.class, 1l);
	}

	public static void save(Agencia agencia) {

		Session session = HibernateUtils.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.save(agencia);
		session.getTransaction().commit();

	}

}
