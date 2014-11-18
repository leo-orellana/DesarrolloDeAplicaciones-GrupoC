package ar.edu.unq.desapp.grupoc.model;

public class OperationBankAccount extends Operation {

    private BankOperation bankOperation;
    
    public OperationBankAccount(){
    	super();
    }

    public OperationBankAccount(Movement movement, Double amount,
            BankOperation bankOperation) {
        super(movement, amount);
        setBankOperation(bankOperation);
    }

    @Override
    public void execute(Account aAccount) {
        this.getBankOperation().execute((AccountBank) aAccount, this);
    }

    public BankOperation getBankOperation() {
        return bankOperation;
    }

    public void setBankOperation(BankOperation typeOperationBank) {
        this.bankOperation = typeOperationBank;
    }

	@Override
	public void setConsolidateProperties(Transaction transaction) {
		getBankOperation().setConsolidateProperties(transaction);
	}

}
