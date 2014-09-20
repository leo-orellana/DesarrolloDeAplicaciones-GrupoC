package ar.edu.unq.desapp.grupoc.model;

public class BankOperationCredit extends BankOperation {

    /**
     * This method updated a accrued money and a balance of the AccountBank
     */
    @Override
    public void execute(AccountBank account,
            OperationBankAccount operationBankAccount) {
        account.setAccrued(operationBankAccount.getMovement().processAmount(
                account.getAccrued(), operationBankAccount.getAmount()));
        operationBankAccount.updateBalance(account);
    }

	@Override
	public Boolean isCredit() {
		return new Boolean(true);
	}

}
