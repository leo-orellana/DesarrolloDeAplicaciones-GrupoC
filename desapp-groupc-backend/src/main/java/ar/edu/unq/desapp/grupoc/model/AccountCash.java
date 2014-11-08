package ar.edu.unq.desapp.grupoc.model;

public class AccountCash extends Account {

	public AccountCash(){
		super();
		this.setName("Cash");
	}

	@Override
	public Operation getNewOperation(double amount, Movement movement,
			BankOperation bankOperation) {
		return new OperationCashAccount(movement, amount);
	}
}
