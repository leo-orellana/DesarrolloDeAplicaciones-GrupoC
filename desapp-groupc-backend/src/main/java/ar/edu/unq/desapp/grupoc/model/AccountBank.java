package ar.edu.unq.desapp.grupoc.model;

public class AccountBank extends Account {

	public AccountBank(){
		this.setName("Bank");
	}
	
    private Double available;
    private Double accrued;

    public AccountBank(Double available, Double accrued) {
        super();
        this.setAvailable(available);
        this.setAccrued(accrued);
        this.setName("Bank");
    }

    // ////////

    public Double getAvailable() {
        return available;
    }

    public void setAvailable(Double available) {
        this.available = available;
    }

    public Double getAccrued() {
        return accrued;
    }

    public void setAccrued(Double accrued) {
        this.accrued = accrued;
    }
    /**
     * This method condolidate a transaction if it should be consolidate.
     * @param transaction
     */
	public Transaction consolidateTransaction(Transaction transaction) {
		Double amountAccrued = transaction.getOperationBankAccount().getAmount();
		setAvailable(getAvailable() + amountAccrued);
		setAccrued(getAccrued() - amountAccrued);
		transaction.setWasConsolidated(true);
		return transaction;
	}

	@Override
	public Operation getNewOperation(double amount, Movement movement,
			BankOperation bankOperation) {
		return new OperationBankAccount(movement, amount, bankOperation);
	}

}
