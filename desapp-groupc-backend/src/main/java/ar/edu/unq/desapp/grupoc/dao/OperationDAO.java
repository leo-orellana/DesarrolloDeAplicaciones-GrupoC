package ar.edu.unq.desapp.grupoc.dao;

import java.util.List;

import ar.edu.unq.desapp.grupoc.model.Operation;

public class OperationDAO extends HibernateGenericDAO<Operation> implements GenericDAO<Operation>{

	private static final long serialVersionUID = 1L;

	@Override
	public List<Operation> filterByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<Operation> getDomainClass() {
		return Operation.class;
	}

}
