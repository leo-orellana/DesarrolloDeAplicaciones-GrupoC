package ar.edu.unq.desapp.grupoc;

public class AccountManager {

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

    /**
     * 
     * @return The consolidation of the account at the moment. This method check
     *         the money accrued and your date and it is equal to today,
     *         transform this amount in available.
     */
    public Integer accountConsolidation() {
        return null;
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
    public Double getCheckingAccountBalance() {
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
}
