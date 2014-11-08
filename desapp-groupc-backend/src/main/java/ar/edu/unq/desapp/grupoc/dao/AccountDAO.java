package ar.edu.unq.desapp.grupoc.dao;

import java.util.List;

import ar.edu.unq.desapp.grupoc.model.Account;

public class AccountDAO extends HibernateGenericDAO<Account>
implements GenericDAO<Account>{

	private static final long serialVersionUID = 1L;

	@Override
	public List<Account> filterByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<Account> getDomainClass() {
		return Account.class;
	}

}
