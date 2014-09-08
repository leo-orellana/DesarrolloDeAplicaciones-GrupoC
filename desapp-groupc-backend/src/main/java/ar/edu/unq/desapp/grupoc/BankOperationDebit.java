package ar.edu.unq.desapp.grupoc;

public class BankOperationDebit extends BankOperation {

	/**
	 * This method updated a available money and a balance of the AccountBank
	 */
	@Override
	public void execute(AccountBank aAccount, Movement aMovement,Integer aAmount) {
		aAccount.setAvailable(aMovement.processAmount(aAccount.getAvailable(), aAmount));
	}


}
