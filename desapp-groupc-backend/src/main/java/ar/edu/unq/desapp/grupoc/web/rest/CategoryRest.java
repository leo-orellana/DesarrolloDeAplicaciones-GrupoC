package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.Category;
import ar.edu.unq.desapp.grupoc.model.Movement;
import ar.edu.unq.desapp.grupoc.services.CategoryService;
import ar.edu.unq.desapp.grupoc.services.MovementService;

@Service
@Path("/categoryService")
public class CategoryRest {

	private CategoryService categoryService;
	private MovementService movementService;

	@GET
	@Path("/category/{id}")
	@Produces("application/json")
	public Category getCategory(@PathParam("id") final int id) {
		Category cat = getCategoryService().getById(id);
		return cat;
	}
	
	@GET
	@Path("/delete/{id}")
	@Produces("application/json")
	public Category deleteCategory(@PathParam("id") final int id) {
		Category c = this.getCategory(id);
		getCategoryService().delete(c);
		return c;
	}

	@GET
	@Path("/categories")
	@Produces("application/json")
	public List<Category> getCategories() {
		return getCategoryService().retriveAll();
	}
	
	@GET
	@Path("/categoriesEgress")
	@Produces("application/json")
	public List<Category> getCategoriesEgress() {
		return getCategoryService().getCategoriesEgress();
	}
	
	@GET
	@Path("/categoriesIngress")
	@Produces("application/json")
	public List<Category> getCategoriesIngress() {
		return getCategoryService().getCategoriesIngress();
	}

	@GET
	@Path("/filterByName/{name}")
	@Produces("application/json")
	public List<Category> filterByName(@PathParam("name") final String name) {
		return getCategoryService().filterByName(name);
	}

	@GET
	@Path("/save/{name}/{movementId}")
	@Produces("application/json")
	public Category saveCategory(@PathParam("name") final String name,
			@PathParam("movementId") final int movementId) {
		Category cat = new Category();
		Movement mov = getMovementService().getById(movementId);
		cat.setName(name);
		cat.setMovement(mov);
		getCategoryService().save(cat);
		return cat;
	}

	@GET
	@Path("/update/{id}/{name}/{movementId}")
	@Produces("application/json")
	public Category updateCategory(@PathParam("id") final int id,
			@PathParam("name") final String name,
			@PathParam("movementId") final int movementId) {
		Category cat = getCategoryService().getById(id);
		Movement mov = getMovementService().getById(movementId);
		cat.setName(name);
		cat.setMovement(mov);
		getCategoryService().update(cat);
		return cat;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public MovementService getMovementService() {
		return movementService;
	}

	public void setMovementService(MovementService movementService) {
		this.movementService = movementService;
	}
}
