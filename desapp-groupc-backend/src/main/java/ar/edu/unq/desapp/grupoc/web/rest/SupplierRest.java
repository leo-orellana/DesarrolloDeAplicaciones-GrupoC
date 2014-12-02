package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.Supplier;
import ar.edu.unq.desapp.grupoc.services.SupplierService;

@Service
@Path("/supplierService")
public class SupplierRest {

	private SupplierService supplierService;

	@GET
	@Path("/supplier/{id}")
	@Produces("application/json")
	public Supplier getSupplier(@PathParam("id") final int id) {
		Supplier sup = getSupplierService().getById(id);
		return sup;
	}
	
	@GET
	@Path("/delete/{id}")
	@Produces("application/json")
	public Supplier deleteSupplier(@PathParam("id") final int id) {
		Supplier c = this.getSupplier(id);
		getSupplierService().delete(c);
		return c;
	}

	@GET
	@Path("/suppliers")
	@Produces("application/json")
	public List<Supplier> getSuppliers() {
		return getSupplierService().retriveAll();
	}

	@GET
	@Path("/filterByName/{name}")
	@Produces("application/json")
	public List<Supplier> filterByName(@PathParam("name") final String name) {
		return getSupplierService().filterByName(name);
	}

	@GET
	@Path("/save/{code}/{name}/{cuit}")
	@Produces("application/json")
	public Supplier saveSupplier(@PathParam("code") final String code,
			@PathParam("name") final String name,
			@PathParam("cuit") final String cuit) {
		Supplier sup = new Supplier();
		sup.setCode(code);
		sup.setCompanyName(name);
		sup.setCuit(cuit);
		getSupplierService().save(sup);
		return sup;
	}

	@GET
	@Path("/update/{id}/{code}/{name}/{cuit}")
	@Produces("application/json")
	public Supplier updateSupplier(@PathParam("id") final int id,
			@PathParam("code") final String code,
			@PathParam("name") final String name,
			@PathParam("cuit") final String cuit) {
		Supplier sup = getSupplierService().getById(id);
		sup.setCode(code);
		sup.setCompanyName(name);
		sup.setCuit(cuit);
		getSupplierService().update(sup);
		return sup;
	}
	
	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
}
