package ar.edu.unq.desapp.grupoc.model;

public class AccountChecking extends Account {

	public AccountChecking(){
		super();
		this.setName("Checking");
	}

	@Override
	public Operation getNewOperation(double amount, Movement movement,
			BankOperation bankOperation) {
		return new OperationCheckingAccount(movement, amount);
	}
}
