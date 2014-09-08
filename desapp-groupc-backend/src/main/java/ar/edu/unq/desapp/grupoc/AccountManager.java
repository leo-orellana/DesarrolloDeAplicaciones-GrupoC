package ar.edu.unq.desapp.grupoc;

public class AccountManager {
	
	private AccountCash AccountCash;
	private AccountChecking accountChecking;
	private AccountBank accountbank;
	
	/**
	 * 
	 * @return The consolidation of the account at the moment. This method check the 
	 * money accrued and your date and it is equal to today, transform this amount in 
	 * available.
	 */
	public Integer accountConsolidation()
	{
		return null;
	}
	
	/**
	 * Send the message processOperation at your accounts.
	 * @param aTransaction
	 */
	public void inputTransaction(Transaction aTransaction)
	{
		this.getAccountbank().processOperation(aTransaction.getOperationBankAccount());
		this.getAccountCash().processOperation(aTransaction.getOperationCashAccount());
		this.getAccountChecking().processOperation(aTransaction.getOperationCheckingAccount());
	}
	
	/**
	 * 
	 * @return The balance of the cash account.
	 */
	public Integer getCashBalance()
	{
		return null;
	}

	/**
	 * 
	 * @return The balance of the bank account. This is the sum of available money and 
	 * accrued money.
	 */
	public Integer getBankBalance()
	{
		return null;
	}
	
	/**
	 * 
	 * @return The balance of the checking account.
	 */
	public Integer getCheckingAccountBalance()
	{
		return null;
	}
	
	/**
	 * 
	 * @return The available monew. This money is only in the bank account.
	 */
	public Integer getAvailableMoney()
	{
		return null;
	}
	
	/**
	 * 
	 * @return The accrued money. This money is only in the bank account.
	 */
	public Integer getAccruedMoney()
	{
		return null;
	}

	public AccountCash getAccountCash() {
		return AccountCash;
	}

	public void setAccountCash(AccountCash accountCash) {
		AccountCash = accountCash;
	}

	public AccountChecking getAccountChecking() {
		return accountChecking;
	}

	public void setAccountChecking(AccountChecking accountChecking) {
		this.accountChecking = accountChecking;
	}

	public AccountBank getAccountbank() {
		return accountbank;
	}

	public void setAccountbank(AccountBank accountbank) {
		this.accountbank = accountbank;
	}
}
