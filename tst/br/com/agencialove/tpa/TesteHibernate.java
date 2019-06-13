package br.com.agencialove.tpa;

import org.hibernate.Session;

import br.com.agencialove.tpa.model.Encomenda;
import br.com.agencialove.tpa.utils.HibernateUtils;


 
public class TesteHibernate
{
   public static void main(String[] args)
   {
      Session session = HibernateUtils.getSessionFactory().openSession();
      session.beginTransaction();
      // Add new Employee object
      Encomenda emp = new Encomenda();
      emp.setDestinatarioNome("Felipe Godoy");
      session.save(emp);
      
      Encomenda empq = new Encomenda();
      empq.setDestinatarioNome("Sabino Godoy");
      session.save(empq);
      
      
     
      
      Encomenda emp3 = new Encomenda();
      emp3.setDestinatarioNome("Ana Godoy");
      session.save(emp3);
      
      Encomenda empq4 = new Encomenda();
      empq4.setDestinatarioNome("Gustavo Godoy");
      session.save(empq4);
      
      session.getTransaction().commit();
      HibernateUtils.shutdown();
   }
}
