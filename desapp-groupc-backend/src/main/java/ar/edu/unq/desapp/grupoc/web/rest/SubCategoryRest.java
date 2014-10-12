package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.Category;
import ar.edu.unq.desapp.grupoc.model.Egress;
import ar.edu.unq.desapp.grupoc.model.Ingress;
import ar.edu.unq.desapp.grupoc.model.Subcategory;
import ar.edu.unq.desapp.grupoc.services.SubCategoryService;

@Service
@Path("/subcategoryService")
public class SubCategoryRest {
	
	private SubCategoryService subCategoryService;
	
	@GET
	@Path("/subcategories")
	@Produces("application/json")
	public List<Subcategory> getCategories() {
		this.initializeContext();
		return getSubCategoryService().retriveAll();
	}

	@GET
	@Path("/filterByName/{name}")
	@Produces("application/json")
	public List<Subcategory> filterByName(@PathParam("name") final String name) {
		return getSubCategoryService().filterByName(name);
	}
	
	
	public SubCategoryService getSubCategoryService() {
		return subCategoryService;
	}

	public void setSubCategoryService(SubCategoryService subCategoryService) {
		this.subCategoryService = subCategoryService;
	}
	
	private void initializeContext() {
		Ingress ingress = new Ingress();
		ingress.setName("Ingress");
		Egress egress = new Egress();
		egress.setName("Egress");

		Category ventas = new Category();
		ventas.setName("Ventas");
		ventas.setMovement(ingress);

		Category rifas = new Category();
		rifas.setName("Rifas");
		rifas.setMovement(ingress);
		
		Subcategory televisores = new Subcategory();
		televisores.setCategory(ventas);
		televisores.setName("Televisores");
		
		Subcategory autos = new Subcategory();
		autos.setCategory(ventas);
		autos.setName("Autos");
		
		getSubCategoryService().save(televisores);
		getSubCategoryService().save(autos);
	}

}
