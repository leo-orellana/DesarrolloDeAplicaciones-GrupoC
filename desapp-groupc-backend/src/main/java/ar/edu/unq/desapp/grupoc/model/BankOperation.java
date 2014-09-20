package ar.edu.unq.desapp.grupoc.model;

public abstract class BankOperation {

    public abstract void execute(AccountBank account,
            OperationBankAccount operationBankAccount);

	public abstract Boolean isCredit();

}
