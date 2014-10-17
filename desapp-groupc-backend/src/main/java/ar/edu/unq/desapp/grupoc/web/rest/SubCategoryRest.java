package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.Category;
import ar.edu.unq.desapp.grupoc.model.Subcategory;
import ar.edu.unq.desapp.grupoc.services.CategoryService;
import ar.edu.unq.desapp.grupoc.services.SubCategoryService;

@Service
@Path("/subcategoryService")
public class SubCategoryRest {
	
	private SubCategoryService subCategoryService;
	private CategoryService categoryService;
	
	@GET
	@Path("/subcategory/{id}")
	@Produces("application/json")
	public Subcategory getSubCategory(@PathParam("id") final int id) {
		Subcategory subcat = getSubCategoryService().getById(id);
		return subcat;
	}
	
	@GET
	@Path("/delete/{id}")
	@Produces("application/json")
	public Subcategory deleteSubCategory(@PathParam("id") final int id) {
		Subcategory subcat = this.getSubCategory(id);
		getSubCategoryService().delete(subcat);
		return subcat;
	}
	
	@GET
	@Path("/subcategories")
	@Produces("application/json")
	public List<Subcategory> getSubCategories() {
		return getSubCategoryService().retriveAll();
	}

	@GET
	@Path("/filterByName/{name}")
	@Produces("application/json")
	public List<Subcategory> filterByName(@PathParam("name") final String name) {
		return getSubCategoryService().filterByName(name);
	}
	
	@GET
	@Path("/save/{name}/{categoryId}")
	@Produces("application/json")
	public Subcategory saveSubcategory(@PathParam("name") final String name,
			@PathParam("categoryId") final int categoryId) {
		Subcategory subcat = new Subcategory();
		Category cat = getCategoryService().getById(categoryId);
		subcat.setName(name);
		subcat.setCategory(cat);
		getSubCategoryService().save(subcat);
		return subcat;
	}
	
	@GET
	@Path("/update/{id}/{name}/{categoryId}")
	@Produces("application/json")
	public Subcategory updateSubcategory(@PathParam("id") final int id,
			@PathParam("name") final String name,
			@PathParam("categoryId") final int categoryId) {
		Subcategory subcat = getSubCategoryService().getById(id);
		Category cat = getCategoryService().getById(categoryId);
		subcat.setName(name);
		subcat.setCategory(cat);
		getSubCategoryService().update(subcat);
		return subcat;
	}

	
	public SubCategoryService getSubCategoryService() {
		return subCategoryService;
	}

	public void setSubCategoryService(SubCategoryService subCategoryService) {
		this.subCategoryService = subCategoryService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
}
