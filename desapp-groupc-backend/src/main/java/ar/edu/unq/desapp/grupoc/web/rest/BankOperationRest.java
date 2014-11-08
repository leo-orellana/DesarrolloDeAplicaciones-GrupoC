package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.BankOperation;
import ar.edu.unq.desapp.grupoc.services.BankOperationService;

@Service
@Path("/bankOperationService")
public class BankOperationRest {

	private BankOperationService bankOperationService;
	@GET
	@Path("/operations")
	@Produces("application/json")
	public List<BankOperation> getOperations() {
		return getBankOperationService().retriveAll();
	}
	public BankOperationService getBankOperationService() {
		return bankOperationService;
	}
	public void setBankOperationService(BankOperationService bankOperationService) {
		this.bankOperationService = bankOperationService;
	}
}
