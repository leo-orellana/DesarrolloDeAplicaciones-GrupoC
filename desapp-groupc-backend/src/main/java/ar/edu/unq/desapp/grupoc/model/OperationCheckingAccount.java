package ar.edu.unq.desapp.grupoc.model;

public class OperationCheckingAccount extends Operation {

    public OperationCheckingAccount(Movement movement, Double amount) {
        super(movement, amount);
    }
    
    public OperationCheckingAccount(){
    	super();
    }

    @Override
	public void setConsolidateProperties(Transaction transaction) {
		transaction.setWasConsolidated(true);
		transaction.setShouldBeConsolidated(false);
	}
}
