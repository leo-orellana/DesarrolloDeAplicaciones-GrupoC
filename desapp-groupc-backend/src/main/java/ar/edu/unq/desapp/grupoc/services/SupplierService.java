package ar.edu.unq.desapp.grupoc.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoc.dao.SupplierDAO;
import ar.edu.unq.desapp.grupoc.model.Supplier;

public class SupplierService extends GenericService<Supplier>{

	private static final long serialVersionUID = -3561405315314185094L;
	
	@Transactional
	public List<Supplier> filterByCode(String code) {
		List<Supplier> list = ((SupplierDAO)this.dao).filterByCode(code);
		return list;
	}

}
