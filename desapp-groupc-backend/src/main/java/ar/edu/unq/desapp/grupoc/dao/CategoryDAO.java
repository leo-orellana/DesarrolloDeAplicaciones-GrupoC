package ar.edu.unq.desapp.grupoc.dao;

import ar.edu.unq.desapp.grupoc.model.Category;

public class CategoryDAO extends HibernateGenericDAO<Category> implements
		GenericDAO<Category> {

	private static final long serialVersionUID = 939209274312970536L;

	@Override
	protected Class<Category> getDomainClass() {
		return Category.class;
	}

}
