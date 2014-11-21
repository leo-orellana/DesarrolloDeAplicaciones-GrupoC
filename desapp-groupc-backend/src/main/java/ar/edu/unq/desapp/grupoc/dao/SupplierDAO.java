package ar.edu.unq.desapp.grupoc.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupoc.model.Supplier;

public class SupplierDAO extends HibernateGenericDAO<Supplier> implements
		GenericDAO<Supplier> {

	private static final long serialVersionUID = 939209274312970536L;

	@Override
	protected Class<Supplier> getDomainClass() {
		return Supplier.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<Supplier> filterByName(String name){
		List<Supplier> c = this.getSession().createCriteria(Supplier.class)
				.add(Restrictions.ilike("companyName", name, MatchMode.ANYWHERE)).list();
		
		return c;
	}
}

