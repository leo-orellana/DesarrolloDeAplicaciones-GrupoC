package ar.edu.unq.desapp.grupoc.model;

public class BankOperationDebit extends BankOperation {

	public BankOperationDebit(){
		super();
		this.setName("Debit");
	}
	
    /**
     * This method updated a available money and a balance of the AccountBank
     */
    @Override
    public void execute(AccountBank account,
            OperationBankAccount operationBankAccount) {
        account.setAvailable(operationBankAccount.getMovement().processAmount(
                account.getAvailable(), operationBankAccount.getAmount()));
        operationBankAccount.updateBalance(account);
    }

	@Override
	public Boolean isCredit() {
		return new Boolean(false);
	}

}
