package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.Transaction;
import ar.edu.unq.desapp.grupoc.services.TransactionService;

@Service
@Path("/transactionService")
public class TransactionRest {

	private TransactionService transactionService;
	
	@GET
	@Path("/transactions")
	@Produces("application/json")
	public List<Transaction> getCategories() {
		return getTransactionService().retriveAll();
	}
	
	public TransactionService getTransactionService() {
		return transactionService;
	}
	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
}
