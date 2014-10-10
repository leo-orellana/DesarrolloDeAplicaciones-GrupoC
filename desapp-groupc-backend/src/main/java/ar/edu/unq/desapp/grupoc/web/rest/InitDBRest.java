package ar.edu.unq.desapp.grupoc.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.Category;
import ar.edu.unq.desapp.grupoc.model.Egress;
import ar.edu.unq.desapp.grupoc.model.Ingress;
import ar.edu.unq.desapp.grupoc.services.CategoryService;
import ar.edu.unq.desapp.grupoc.services.MovementService;

@Service
@Path("/db")
public class InitDBRest {

	private CategoryService categoryService;
	private MovementService movementService;
	
	@GET
	@Path("/init")
	public void initDB() {
		
		// CREATE MOVEMENTS
		Ingress ingress = new Ingress();
		Egress egress = new Egress();
		
		getMovementService().save(ingress);
		getMovementService().save(egress);
		
		// CREATE CATEGORIES
		Category pagoSueldos = new Category();
		pagoSueldos.setName("Pago Sueldos");
		pagoSueldos.setMovement(egress);
		
		Category ventas = new Category();
		ventas.setName("Ventas");
		ventas.setMovement(ingress);
		
		getCategoryService().save(pagoSueldos);
		getCategoryService().save(ventas);
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
