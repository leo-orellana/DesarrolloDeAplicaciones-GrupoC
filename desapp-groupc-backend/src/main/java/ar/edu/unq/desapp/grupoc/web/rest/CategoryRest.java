package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.Category;
import ar.edu.unq.desapp.grupoc.services.CategoryService;

@Service
@Path("/categories")
public class CategoryRest {

	private CategoryService categoryService;

	@GET
	@Path("/all")
	@Produces("application/json")
	public List<Category> getCategories() {
		return getCategoryService().retriveAll();
	}

	@GET
	@Path("/filterByName/{name}")
	@Produces("application/json")
	public List<Category> filterByName(@PathParam("name") final String name) {
		return getCategoryService().filterByName(name);
	}
	
	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

}
