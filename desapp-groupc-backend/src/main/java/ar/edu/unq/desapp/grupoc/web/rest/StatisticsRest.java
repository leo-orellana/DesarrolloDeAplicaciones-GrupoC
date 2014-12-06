package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.services.CategoryService;
import ar.edu.unq.desapp.grupoc.services.TransactionService;

@Service
@Path("/statistics")
public class StatisticsRest {

	public CategoryService categoryService;
	public TransactionService transactionService;
	
	@GET
	@Path("/expensesByCategory")
	@Produces("application/json")
	public HashMap<String, Double> getExpensesByCategory() {
		return getCategoryService().getExpensesByCategory(getTransactionService().retriveAll());
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public TransactionService getTransactionService() {
		return transactionService;
	}

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
}
