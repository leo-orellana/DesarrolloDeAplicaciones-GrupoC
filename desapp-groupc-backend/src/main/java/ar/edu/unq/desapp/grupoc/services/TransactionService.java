package ar.edu.unq.desapp.grupoc.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoc.dao.TransactionDAO;
import ar.edu.unq.desapp.grupoc.model.Subcategory;
import ar.edu.unq.desapp.grupoc.model.Transaction;

public class TransactionService extends GenericService<Transaction>{

	private static final long serialVersionUID = 1L;

	@Transactional
	public List<Transaction> filterByConcept(String concept) {
		List<Transaction> list = this.dao.filterByName(concept);
		return list;
	}

	@Transactional
	public void updateAll(List<Transaction> consolidateds) {
		for (Transaction transaction : consolidateds) {
			update(transaction);
		}
	}
	
	public Transaction getByReceiptId(int receiptId){
		Transaction t = ((TransactionDAO)this.dao).getByReceiptId(receiptId);
		return t;
	}
	
	public List<Transaction> filterByCategory(Subcategory subcategory) {
		return ((TransactionDAO) getDao()).filterBySubCategory(subcategory);
	}
}
