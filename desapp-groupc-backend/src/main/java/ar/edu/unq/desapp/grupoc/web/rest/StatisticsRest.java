package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.services.StatisticService;
import ar.edu.unq.desapp.grupoc.services.TransactionService;

@Service
@Path("/statistics")
public class StatisticsRest {

	public StatisticService statisticService;
	public TransactionService transactionService;
	
	@GET
	@Path("/expensesByCategory")
	@Produces("application/json")
	public HashMap<String, Double> getExpensesByCategory() {
		return getStatisticService().getExpensesByCategory(getTransactionService().retriveAll());
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

}
