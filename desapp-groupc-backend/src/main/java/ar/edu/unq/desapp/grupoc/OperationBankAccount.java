package ar.edu.unq.desapp.grupoc;

public class OperationBankAccount extends Operation{

	private BankOperation bankOperation;
	
	@Override
	public void execute(Account aAccount)
	{
		this.getBankOperation().execute((AccountBank)aAccount,this);
	}

	public BankOperation getBankOperation() {
		return bankOperation;
	}

	public void setTypeOperationBank(BankOperation typeOperationBank) {
		this.bankOperation = typeOperationBank;
	}

}
