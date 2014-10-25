package ar.edu.unq.desapp.grupoc.dao;

import java.util.List;

import ar.edu.unq.desapp.grupoc.model.AccountManager;

public class AccountManagerDAO extends HibernateGenericDAO<AccountManager>
implements GenericDAO<AccountManager>{

	private static final long serialVersionUID = 1L;

	@Override
	public List<AccountManager> filterByName(String name) {
		return null;
	}

	@Override
	protected Class<AccountManager> getDomainClass() {
		return AccountManager.class;
	}

}
