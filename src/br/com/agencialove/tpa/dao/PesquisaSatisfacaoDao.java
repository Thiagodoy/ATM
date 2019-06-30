package br.com.agencialove.tpa.dao;

import org.hibernate.Session;

import br.com.agencialove.tpa.model.PesquisaSatisfacao;
import br.com.agencialove.tpa.utils.HibernateUtils;

public class PesquisaSatisfacaoDao {
	

	public static void save(PesquisaSatisfacao pesquisa) {

		Session session = HibernateUtils.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.save(pesquisa);
		session.getTransaction().commit();

	}

}
