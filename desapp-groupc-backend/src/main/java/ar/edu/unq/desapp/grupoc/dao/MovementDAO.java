package ar.edu.unq.desapp.grupoc.dao;

import ar.edu.unq.desapp.grupoc.model.Movement;

public class MovementDAO extends HibernateGenericDAO<Movement> implements
		GenericDAO<Movement> {

	private static final long serialVersionUID = -1148225125271202341L;

	@Override
	protected Class<Movement> getDomainClass() {
		return Movement.class;
	}

}
