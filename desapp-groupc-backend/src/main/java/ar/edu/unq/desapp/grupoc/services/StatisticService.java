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
		List<Transaction> transactions = filterEgress(allTransactions);
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

	public HashMap<String, Double> getIntgressByShift(
			List<Transaction> allTransactions) {
		List<Transaction> transactions = filterIngress(allTransactions);
		HashMap<String, Double> hash = new HashMap<String, Double>();
		for (Transaction t : transactions) {
			String shift = t.getTime().name();
			if (hash.containsKey(shift)) {
				hash.put(shift, hash.get(shift)
						+ t.getOperationBankAccount().getAmount()
						+ t.getOperationCashAccount().getAmount()
						+ t.getOperationCheckingAccount().getAmount());
			} else {
				hash.put(shift, t.getOperationBankAccount().getAmount()
						+ t.getOperationCashAccount().getAmount()
						+ t.getOperationCheckingAccount().getAmount());
			}
		}
		return hash;
	}
	
	public HashMap<String, Double> getEgressBySubcategoriesInCategory(
			List<Transaction> allTransactions, Category category) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		for(Transaction t : allTransactions){
			if((!t.getSubcategory().getCategory().getMovement().isIngress()) && (t.getSubcategory().getCategory().getName() == category.getName())){
				transactions.add(t);
			}
		}
		HashMap<String, Double> hash = new HashMap<String, Double>();
		for (Transaction t : transactions) {
			String name = t.getSubcategory().getName();
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

	public HashMap<String, Double> getIngressBySubcategoriesInCategory(
			List<Transaction> allTransactions, Category category) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		for(Transaction t : allTransactions){
			if((t.getSubcategory().getCategory().getMovement().isIngress()) && (t.getSubcategory().getCategory().getName() == category.getName())){
				transactions.add(t);
			}
		}
		HashMap<String, Double> hash = new HashMap<String, Double>();
		for (Transaction t : transactions) {
			String name = t.getSubcategory().getName();
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
	
	public HashMap<String, Double> getEgressByShift(
			List<Transaction> allTransactions) {
		List<Transaction> transactions = filterEgress(allTransactions);
		HashMap<String, Double> hash = new HashMap<String, Double>();
		for (Transaction t : transactions) {
			String shift = t.getTime().name();
			if (hash.containsKey(shift)) {
				hash.put(shift, hash.get(shift)
						+ t.getOperationBankAccount().getAmount()
						+ t.getOperationCashAccount().getAmount()
						+ t.getOperationCheckingAccount().getAmount());
			} else {
				hash.put(shift, t.getOperationBankAccount().getAmount()
						+ t.getOperationCashAccount().getAmount()
						+ t.getOperationCheckingAccount().getAmount());
			}
		}
		return hash;
	}
	
	public List<Transaction> filterIngress(List<Transaction> transactions){
		List<Transaction> result = new ArrayList<Transaction>();
		for(Transaction t: transactions){
			if(t.getSubcategory().getCategory().getMovement().isIngress()){
				result.add(t);
			}
		}
		return result;
	}
	
	public List<Transaction> filterEgress(List<Transaction> transactions){
		List<Transaction> result = new ArrayList<Transaction>();
		for(Transaction t: transactions){
			if(!t.getSubcategory().getCategory().getMovement().isIngress()){
				result.add(t);
			}
		}
		return result;
	}



}
