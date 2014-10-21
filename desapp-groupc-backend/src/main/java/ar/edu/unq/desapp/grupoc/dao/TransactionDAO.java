package ar.edu.unq.desapp.grupoc.dao;

import java.util.List;

import ar.edu.unq.desapp.grupoc.model.Transaction;

public class TransactionDAO extends HibernateGenericDAO<Transaction> implements
		GenericDAO<Transaction> {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Transaction> filterByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<Transaction> getDomainClass() {
		return Transaction.class;
	}

}
