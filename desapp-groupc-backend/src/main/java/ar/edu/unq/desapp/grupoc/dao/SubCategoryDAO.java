package ar.edu.unq.desapp.grupoc.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupoc.model.Category;
import ar.edu.unq.desapp.grupoc.model.Subcategory;

public class SubCategoryDAO extends HibernateGenericDAO<Subcategory> implements
		GenericDAO<Subcategory> {

	private static final long serialVersionUID = 939209274312970536L;

	@Override
	protected Class<Subcategory> getDomainClass() {
		return Subcategory.class;
	}

	@SuppressWarnings("unchecked")
	public List<Subcategory> filterByName(String name){
		List<Subcategory> c = this.getSession().createCriteria(Subcategory.class)
				.add(Restrictions.like("name", name, MatchMode.EXACT)).list();
		
		return c;
	}
	
	@SuppressWarnings("unchecked")
	public List<Subcategory> filterByCategory(Category category){
		List<Subcategory> l = this.getSession().createCriteria(Subcategory.class)
				.add(Restrictions.eq("category", category)).list();
		return l;
	}
}