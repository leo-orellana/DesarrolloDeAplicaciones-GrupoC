package ar.edu.unq.desapp.grupoc.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupoc.model.Category;

public class CategoryDAO extends HibernateGenericDAO<Category> implements
		GenericDAO<Category> {

	private static final long serialVersionUID = 939209274312970536L;

	@Override
	protected Class<Category> getDomainClass() {
		return Category.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> filterByName(String name){
		List<Category> c = this.getSession().createCriteria(Category.class)
				.add(Restrictions.like("name", name, MatchMode.EXACT)).list();
		return c;
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> filterByMovement(int movementId){
		List<Category> c = this.getSession().createCriteria(Category.class)
				.add(Restrictions.eq("movement.id", movementId)).list();
		return c;
	}
}
