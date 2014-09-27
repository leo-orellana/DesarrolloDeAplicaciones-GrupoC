package ar.edu.unq.desapp.grupoc.hibernate.test;

import org.hibernate.Session;

import ar.edu.unq.desapp.grupoc.model.Category;
import ar.edu.unq.desapp.grupoc.model.Egress;
import ar.edu.unq.desapp.grupoc.model.Ingress;
import ar.edu.unq.desapp.grupoc.utils.HibernateUtil;


public class HibernateTest {

	/**
	 * Create a objects Movements and Categories and save its.
	 * @param args
	 */
	public static void main(String[] args) {

		Session session = HibernateUtil.openSession();
		
		Ingress ingress = new Ingress();
		ingress.setName("Ingress");
		Egress egress = new Egress();
		egress.setName("Egress");
		
		// Se crean las categorias
		Category ventas = new Category();
		ventas.setName("Ventas");
		ventas.setMovement(ingress);
		
		session.save(ventas);

		Category rifas = new Category();
		rifas.setName("Rifas");
		rifas.setMovement(ingress);
		
		session.save(rifas);
		
		HibernateUtil.closeSession(session);
	}

}
