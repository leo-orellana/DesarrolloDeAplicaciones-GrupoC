package ar.edu.unq.desapp.grupoc.services;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.desapp.grupoc.dao.CategoryDAO;
import ar.edu.unq.desapp.grupoc.model.Category;

public class CategoryService extends GenericService<Category> {

	private static final long serialVersionUID = 8028635714243199268L;

	public List<Category> getCategoriesEgress() {
		List<Category> categories = retriveAll();
		List<Category> result = new ArrayList<>();
		for(Category category : categories){
			if(!category.getMovement().isIngress()){
				result.add(category);
			}
		}
		return result;
	}

	public List<Category> getCategoriesIngress() {
		List<Category> categories = retriveAll();
		List<Category> result = new ArrayList<>();
		for(Category category : categories){
			if(category.getMovement().isIngress()){
				result.add(category);
			}
		}
		return result;
	}

	public List<Category> filterByMovement(int movementId) {
		return ((CategoryDAO) this.dao).filterByMovement(movementId);
	}
	
	public List<Category> findByExample(Category categoryExample) {
		return this.dao.findByExample(categoryExample);
	}
}
