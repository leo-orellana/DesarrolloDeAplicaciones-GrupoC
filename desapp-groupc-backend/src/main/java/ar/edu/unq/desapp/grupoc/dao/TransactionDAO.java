package ar.edu.unq.desapp.grupoc.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupoc.model.Transaction;

public class TransactionDAO extends HibernateGenericDAO<Transaction> implements
		GenericDAO<Transaction> {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Transaction> filterByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Transaction> filterByConcept(String concept) {
		List<Transaction> t = this.getSession().createCriteria(Transaction.class)
				.add(Restrictions.ilike("concept", concept, MatchMode.ANYWHERE)).list();
		
		return t;
	}
	
	@Override
	protected Class<Transaction> getDomainClass() {
		return Transaction.class;
	}

}
