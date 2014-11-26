package ar.edu.unq.desapp.grupoc.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupoc.model.Receipt;

public class ReceiptDAO extends HibernateGenericDAO<Receipt> implements
		GenericDAO<Receipt> {

	private static final long serialVersionUID = 939209274312970536L;

	@Override
	protected Class<Receipt> getDomainClass() {
		return Receipt.class;
	}

	@SuppressWarnings("unchecked")
	public List<Receipt> filterByConcept(String concept) {
		List<Receipt> r = this.getSession().createCriteria(Receipt.class)
				.add(Restrictions.ilike("concept", concept, MatchMode.ANYWHERE)).list();
		
		return r;
	}

	@Override
	public List<Receipt> filterByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
