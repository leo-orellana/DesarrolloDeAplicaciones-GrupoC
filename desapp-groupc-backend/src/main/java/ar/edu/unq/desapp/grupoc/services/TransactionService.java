package ar.edu.unq.desapp.grupoc.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoc.model.Transaction;

public class TransactionService extends GenericService<Transaction>{

	private static final long serialVersionUID = 1L;

	@Transactional
	public List<Transaction> filterByConcept(String concept) {
		List<Transaction> list = this.dao.filterByName(concept);
		return list;
	}
}
