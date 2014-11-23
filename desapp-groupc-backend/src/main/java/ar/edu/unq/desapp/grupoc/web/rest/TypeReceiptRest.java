package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.TypeA;
import ar.edu.unq.desapp.grupoc.model.TypeB;
import ar.edu.unq.desapp.grupoc.model.TypeC;
import ar.edu.unq.desapp.grupoc.model.TypeReceipt;
import ar.edu.unq.desapp.grupoc.model.TypeX;
import ar.edu.unq.desapp.grupoc.services.TypeReceiptService;

@Service
@Path("/typeReceiptService")
public class TypeReceiptRest {

	private TypeReceiptService typeReceiptService;

	@GET
	@Path("/receipts")
	@Produces("application/json")
	public List<TypeReceipt> getReceipts() {
		return getTypeReceiptService().retriveAll();
	}
	
	@GET
	@Path("/save")
	@Produces("application/json")
	public TypeReceipt saveTypeReceipt() {
		TypeA typeA = new TypeA();
		TypeB typeB = new TypeB();
		TypeC typeC = new TypeC();
		TypeX typeX = new TypeX();
		
		getTypeReceiptService().save(typeA);
		getTypeReceiptService().save(typeB);
		getTypeReceiptService().save(typeC);
		getTypeReceiptService().save(typeX);
		return typeC;
	}

	@GET
	@Path("/receipt/{id}")
	@Produces("application/json")
	public TypeReceipt getTypeReceipt(@PathParam("id") final int id) {
		TypeReceipt typeReceipt = getTypeReceiptService().getById(id);
		return typeReceipt;
	}

	public TypeReceiptService getTypeReceiptService() {
		return typeReceiptService;
	}

	public void setTypeReceiptService(TypeReceiptService typeReceiptService) {
		this.typeReceiptService = typeReceiptService;
	}
}
