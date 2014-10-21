package ar.edu.unq.desapp.grupoc.dao;

import java.util.List;

import ar.edu.unq.desapp.grupoc.model.BankOperation;

public class BankOperationDAO extends HibernateGenericDAO<BankOperation>
		implements GenericDAO<BankOperation> {

	private static final long serialVersionUID = 1L;

	@Override
	public List<BankOperation> filterByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<BankOperation> getDomainClass() {
		return BankOperation.class;
	}

}
