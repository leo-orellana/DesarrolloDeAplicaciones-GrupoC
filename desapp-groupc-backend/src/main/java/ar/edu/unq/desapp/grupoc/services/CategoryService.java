package ar.edu.unq.desapp.grupoc.services;

import java.util.HashMap;
import java.util.List;

import ar.edu.unq.desapp.grupoc.model.Category;
import ar.edu.unq.desapp.grupoc.model.Transaction;

public class CategoryService extends GenericService<Category> {

	private static final long serialVersionUID = 8028635714243199268L;

	public HashMap<String, Double> getExpensesByCategory(
			List<Transaction> transactions) {
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
