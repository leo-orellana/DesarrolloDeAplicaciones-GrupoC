package ar.edu.unq.desapp.grupoc.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ar.edu.unq.desapp.grupoc.model.Category;
import ar.edu.unq.desapp.grupoc.model.Transaction;

public class StatisticService extends GenericService<Category>{

	private static final long serialVersionUID = 1L;

	public HashMap<String, Double> getExpensesByCategory(
			List<Transaction> allTransactions) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		for(Transaction t: allTransactions){
			if(!t.getSubcategory().getCategory().getMovement().isIngress()){
				transactions.add(t);
			}
		}
		HashMap<String, Double> hash = new HashMap<String, Double>();
		for (Transaction t : transactions) {
			String name = t.getSubcategory().getCategory().getName();
			if (hash.containsKey(name)) {
				hash.put(name, hash.get(name)
						+ t.getOperationBankAccount().getAmount()
						+ t.getOperationCashAccount().getAmount()
						+ t.getOperationCheckingAccount().getAmount());
			} else {
				hash.put(name, t.getOperationBankAccount().getAmount()
						+ t.getOperationCashAccount().getAmount()
						+ t.getOperationCheckingAccount().getAmount());
			}
		}
		return hash;
	}
}
