package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.services.CategoryService;
import ar.edu.unq.desapp.grupoc.services.StatisticService;
import ar.edu.unq.desapp.grupoc.services.TransactionService;

@Service
@Path("/statistics")
public class StatisticsRest {

	public StatisticService statisticService;
	public TransactionService transactionService;
	public CategoryService categoryService;
	
	@GET
	@Path("/expensesByCategory")
	@Produces("application/json")
	public HashMap<String, Double> getExpensesByCategory() {
		return getStatisticService().getExpensesByCategory(getTransactionService().retriveAll());
	}
	
	@GET
	@Path("/ingressByShift")
	@Produces("application/json")
	public HashMap<String, Double> getIngressByShift() {
		return getStatisticService().getIntgressByShift(getTransactionService().retriveAll());
	}
	
	@GET
	@Path("/egressBySubcategoriesInCategory/{id}")
	@Produces("application/json")
	public HashMap<String, Double> getSubCategory(@PathParam("id") final int id) {
		return getStatisticService().getEgressBySubcategoriesInCategory(
				getTransactionService().retriveAll(),
				getCategoryService().getById(id));
	}

	public StatisticService getStatisticService() {
		return statisticService;
	}

	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}

	public TransactionService getTransactionService() {
		return transactionService;
	}

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

}
