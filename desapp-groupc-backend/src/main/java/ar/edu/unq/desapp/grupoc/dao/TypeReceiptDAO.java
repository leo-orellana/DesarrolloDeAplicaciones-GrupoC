package ar.edu.unq.desapp.grupoc.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupoc.model.TypeReceipt;

public class TypeReceiptDAO extends HibernateGenericDAO<TypeReceipt> implements GenericDAO<TypeReceipt>{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeReceipt> filterByName(String name) {
		List<TypeReceipt> tr = this.getSession().createCriteria(TypeReceipt.class)
				.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE)).list();
		
		return tr;
	}

	@Override
	protected Class<TypeReceipt> getDomainClass() {
		return TypeReceipt.class;
	}

}
