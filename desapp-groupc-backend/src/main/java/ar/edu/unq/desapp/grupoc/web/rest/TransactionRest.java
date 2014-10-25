package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.Time;
import ar.edu.unq.desapp.grupoc.model.Transaction;
import ar.edu.unq.desapp.grupoc.services.CategoryService;
import ar.edu.unq.desapp.grupoc.services.SubCategoryService;
import ar.edu.unq.desapp.grupoc.services.TransactionService;

@Service
@Path("/transactionService")
public class TransactionRest {

	private TransactionService transactionService;
	private SubCategoryService subCategoryService;
	private CategoryService categoryService;
	
	@GET
	@Path("/transaction/{id}")
	@Produces("application/json")
	public Transaction getTransaction(@PathParam("id") final int id) {
		Transaction tran = getTransactionService().getById(id);
		return tran;
	}
	
	@GET
	@Path("/delete/{id}")
	@Produces("application/json")
	public Transaction deleteTransaction(@PathParam("id") final int id) {
		Transaction tran = this.getTransaction(id);
		getTransactionService().delete(tran);
		return tran;
	}
	
	@GET
	@Path("/transactions")
	@Produces("application/json")
	public List<Transaction> getTransactions() {
		return getTransactionService().retriveAll();
	}
	
	@GET
	@Path("/filterByName/{name}")
	@Produces("application/json")
	public List<Transaction> filterByConcept(@PathParam("concept") final String concept) {
		return getTransactionService().filterByConcept(concept);
	}
	
	@GET
	@Path("/save/")
	@Produces("application/json")
	public Transaction saveTransaction(@PathParam("name") final String name){
		Time.valueOf(name.trim().toUpperCase());
		return null;
	}
	
	public TransactionService getTransactionService() {
		return transactionService;
	}
	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
}
