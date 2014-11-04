package ar.edu.unq.desapp.grupoc.services;

import java.util.List;

import ar.edu.unq.desapp.grupoc.dao.SubCategoryDAO;
import ar.edu.unq.desapp.grupoc.model.Category;
import ar.edu.unq.desapp.grupoc.model.Subcategory;

public class SubCategoryService extends GenericService<Subcategory> {

	private static final long serialVersionUID = 8028635714243199268L;

	public List<Subcategory> filterByCategory(Category category) {
		return ((SubCategoryDAO) getDao()).filterByCategory(category);
	}

}
