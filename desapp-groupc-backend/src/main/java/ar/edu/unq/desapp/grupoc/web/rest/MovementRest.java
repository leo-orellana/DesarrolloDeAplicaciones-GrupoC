package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.Egress;
import ar.edu.unq.desapp.grupoc.model.Ingress;
import ar.edu.unq.desapp.grupoc.model.Movement;
import ar.edu.unq.desapp.grupoc.services.MovementService;

@Service
@Path("/movements")
public class MovementRest {

	private MovementService movementService;
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<Movement> getMovements() {
		this.initializeContext();
		return getMovementService().retriveAll();
	}
	
	public void setMovementService(MovementService movementService){
		this.movementService = movementService;
	}
	
	public MovementService getMovementService(){
		return this.movementService;
	}
	
	public void initializeContext() {
		Ingress ingress = new Ingress();
		ingress.setName("Ingress");
		Egress egress = new Egress();
		egress.setName("Egress");

		getMovementService().save(ingress);
		getMovementService().save(egress);
	}
}
