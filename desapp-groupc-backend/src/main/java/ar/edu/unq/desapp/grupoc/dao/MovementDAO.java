package ar.edu.unq.desapp.grupoc.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupoc.model.Movement;

public class MovementDAO extends HibernateGenericDAO<Movement> implements
		GenericDAO<Movement> {

	private static final long serialVersionUID = -1148225125271202341L;

	@Override
	protected Class<Movement> getDomainClass() {
		return Movement.class;
	}

	@SuppressWarnings("unchecked")
	public List<Movement> filterByName(String name){
		List<Movement> c = this.getSession().createCriteria(Movement.class)
				.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE)).list();
		
		return c;
	}
}
