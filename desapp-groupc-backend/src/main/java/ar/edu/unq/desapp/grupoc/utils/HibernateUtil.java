package ar.edu.unq.desapp.grupoc.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;


	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null){
			try {
				// load from different directory
				sessionFactory = new Configuration().configure(
						"/META-INF/hibernate.cfg.xml").buildSessionFactory();
			} catch (Throwable ex) {
				// Make sure you log the exception, as it might be swallowed
				System.err.println("Initial SessionFactory creation failed." + ex);
				throw new ExceptionInInitializerError(ex);
			}
		}
		return sessionFactory;
	}
	
	public static Session openSession(){
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		return session;
	}
	
	public static void closeSession(Session session){
		session.getTransaction().commit();
		session.close();
		getSessionFactory().close();
	}

}