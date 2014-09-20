package ar.edu.unq.desapp.grupoc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This object exist because don't exist a real data base. When the real data base exist, this object will be deleted.
 * @author morellatoariel
 *
 */
public class ServiceDataBase {
	
	private static ServiceDataBase instance;
	private List<AccruedTransaction> transactions = new ArrayList<AccruedTransaction>();
	
	public static ServiceDataBase getInstance(){
		if(instance == null){
			instance = new ServiceDataBase();
		}
		return instance;
	}
	
	public List<AccruedTransaction> getAccruedTransactions(){
		return transactions;
	}

	public void setAccruedTransactions(List<AccruedTransaction> newTransactions){
		transactions = newTransactions;
	}
}
