package ar.edu.unq.desapp.grupoc.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupoc.model.Subcategory;
import ar.edu.unq.desapp.grupoc.model.Transaction;

public class TransactionDAO extends HibernateGenericDAO<Transaction> implements
		GenericDAO<Transaction> {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Transaction> filterByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Transaction> filterByConcept(String concept) {
		List<Transaction> t = this.getSession().createCriteria(Transaction.class)
				.add(Restrictions.ilike("concept", concept, MatchMode.ANYWHERE)).list();
		
		return t;
	}
	
	@SuppressWarnings("unchecked")
	public Transaction getByReceiptId(int receiptId){
		List<Transaction> t = this.getSession().createCriteria(Transaction.class)
				.add(Restrictions.eq("receipt.id", receiptId)).list();
		
		return t.get(0);
	}
	
	@Override
	protected Class<Transaction> getDomainClass() {
		return Transaction.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<Transaction> filterBySubCategory(Subcategory subcategory){
		List<Transaction> l = this.getSession().createCriteria(Transaction.class)
				.add(Restrictions.eq("subcategory", subcategory)).list();
		return l;
	}

}
