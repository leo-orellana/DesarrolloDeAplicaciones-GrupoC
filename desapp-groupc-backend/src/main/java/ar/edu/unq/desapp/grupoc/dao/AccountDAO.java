package ar.edu.unq.desapp.grupoc.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupoc.model.Account;

public class AccountDAO extends HibernateGenericDAO<Account>
implements GenericDAO<Account>{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> filterByName(String name) {
		List<Account> c = this.getSession().createCriteria(Account.class)
				.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE)).list();
		
		return c;
	}

	@Override
	protected Class<Account> getDomainClass() {
		return Account.class;
	}

}
