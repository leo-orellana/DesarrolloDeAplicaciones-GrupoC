package ar.edu.unq.desapp.grupoc;

public class BankOperationCredit extends BankOperation {

	/**
	 * This method updated a accrued money and a balance of the AccountBank
	 */
	@Override
	public void execute(AccountBank aAccount, Movement aMovement,Integer aAmount) {
		aAccount.setAccrued(aMovement.processAmount(aAccount.getAccrued(), aAmount));
		
	}

	

}
