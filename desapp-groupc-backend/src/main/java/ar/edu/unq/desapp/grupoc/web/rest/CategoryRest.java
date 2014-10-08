package ar.edu.unq.desapp.grupoc.web.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.Category;
import ar.edu.unq.desapp.grupoc.model.Egress;
import ar.edu.unq.desapp.grupoc.model.Ingress;
import ar.edu.unq.desapp.grupoc.services.CategoryService;

@Service
@Path("/categories")
public class CategoryRest {

	private CategoryService categoryService;
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<Category> getAllCategories() throws JsonGenerationException, JsonMappingException, IOException{
		
		Ingress ingress = new Ingress();
		ingress.setName("Ingress");
		Egress egress = new Egress();
		egress.setName("Egress");
		
		// Se crean las categorias
		Category ventas = new Category();
		ventas.setName("Ventas");
		ventas.setMovement(ingress);
		
		categoryService.save(ventas);

		Category rifas = new Category();
		rifas.setName("Rifas");
		rifas.setMovement(ingress);
		
		categoryService.save(rifas);
		
		/////
		List<Category> categorias = getCategoryService().retriveAll();
		return categorias;
		//return JSONObject.getInstance().ObjectToJSON(categorias);
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
}
