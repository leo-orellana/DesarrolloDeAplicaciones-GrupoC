package ar.edu.unq.desapp.grupoc.model;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class AccountManager {

	private int id;
	private AccountCash accountCash;
	private AccountChecking accountChecking;
	private AccountBank accountBank;

	public AccountManager(AccountCash accountCash,
			AccountChecking accountChecking, AccountBank accountBank) {
		super();
		this.setAccountCash(accountCash);
		this.setAccountChecking(accountChecking);
		this.setAccountbank(accountBank);
	}

	public AccountManager() {
	}

	
	/**
	 * Consolidate Account v2.0
	 */

	public List<Transaction> consolidateAccount(List<Transaction> transactions) {
		DateTime today = new DateTime();
		List<Transaction> consolidateds = new ArrayList<Transaction>();
		for (Transaction transaction : transactions) {
			if (!transaction.getWasConsolidated()
					&& transaction.getShouldBeConsolidated()
					&& today.isAfter(new DateTime(transaction.getDate())
							.plusDays(15))) {
				Transaction t = getAccountbank().consolidateTransaction(transaction);
				consolidateds.add(t);
			}
		}
		return consolidateds;
	}

	/**
	 * Send the message processOperation at your accounts.
	 * 
	 * @param aTransaction
	 */
	public void inputTransaction(Transaction aTransaction) {
		this.getAccountbank().processOperation(
				aTransaction.getOperationBankAccount());
		this.getAccountCash().processOperation(
				aTransaction.getOperationCashAccount());
		this.getAccountChecking().processOperation(
				aTransaction.getOperationCheckingAccount());
	}

	/**
	 * 
	 * @return The balance of the cash account.
	 */
	public Double getCashBalance() {
		return getAccountCash().getBalance();
	}

	/**
	 * 
	 * @return The balance of the bank account. This is the sum of available
	 *         money and accrued money.
	 */
	public Double getBankBalance() {
		return getAccountbank().getBalance();
	}

	/**
	 * 
	 * @return The balance of the checking account.
	 */
	public Double getCheckingBalance() {
		return getAccountChecking().getBalance();
	}

	/**
	 * 
	 * @return The available monew. This money is only in the bank account.
	 */
	public Double getAvailableMoney() {
		return getAccountbank().getAvailable();
	}

	/**
	 * 
	 * @return The accrued money. This money is only in the bank account.
	 */
	public Double getAccruedMoney() {
		return getAccountbank().getAccrued();
	}

	public AccountCash getAccountCash() {
		return accountCash;
	}

	public void setAccountCash(AccountCash accountCash) {
		this.accountCash = accountCash;
	}

	public AccountChecking getAccountChecking() {
		return accountChecking;
	}

	public void setAccountChecking(AccountChecking accountChecking) {
		this.accountChecking = accountChecking;
	}

	public AccountBank getAccountbank() {
		return accountBank;
	}

	public void setAccountbank(AccountBank accountBank) {
		this.accountBank = accountBank;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
