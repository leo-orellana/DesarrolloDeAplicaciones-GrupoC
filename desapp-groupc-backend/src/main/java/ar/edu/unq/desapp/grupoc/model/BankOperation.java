package ar.edu.unq.desapp.grupoc.model;

public abstract class BankOperation {

	private int id;
	
	public BankOperation(){}
	
    public abstract void execute(AccountBank account,
            OperationBankAccount operationBankAccount);

	public abstract Boolean isCredit();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
