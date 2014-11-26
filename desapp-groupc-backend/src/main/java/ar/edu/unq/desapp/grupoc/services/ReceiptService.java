package ar.edu.unq.desapp.grupoc.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoc.model.Receipt;

public class ReceiptService extends GenericService<Receipt> {

	private static final long serialVersionUID = -5255055061327455291L;

	@Transactional
	public List<Receipt> filterByConcept(String concept) {
		List<Receipt> list = this.dao.filterByName(concept);
		return list;
	}
}
