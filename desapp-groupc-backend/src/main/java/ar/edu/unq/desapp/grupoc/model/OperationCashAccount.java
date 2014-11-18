package ar.edu.unq.desapp.grupoc.model;

public class OperationCashAccount extends Operation {

    public OperationCashAccount(Movement movement, Double amount) {
        super(movement, amount);
    }
    
    public OperationCashAccount(){
    	super();
    }

    @Override
	public void setConsolidateProperties(Transaction transaction) {
		transaction.setWasConsolidated(true);
		transaction.setShouldBeConsolidated(false);
	}
}
