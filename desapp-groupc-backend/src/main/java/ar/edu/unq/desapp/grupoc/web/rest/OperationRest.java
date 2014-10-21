package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.Operation;
import ar.edu.unq.desapp.grupoc.services.OperationService;

@Service
@Path("/operationService")
public class OperationRest {
	
	public OperationService operationService;
	
	@GET
	@Path("/operations")
	@Produces("application/json")
	public List<Operation> getCategories() {
		return getOperationService().retriveAll();
	}

	public OperationService getOperationService() {
		return operationService;
	}

	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}

}
