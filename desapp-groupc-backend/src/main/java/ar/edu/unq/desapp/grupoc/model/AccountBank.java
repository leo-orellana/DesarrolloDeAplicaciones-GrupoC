package ar.edu.unq.desapp.grupoc.model;

public class AccountBank extends Account {

	public AccountBank(){}
	
    private Double available;
    private Double accrued;

    public AccountBank(Double available, Double accrued) {
        super();
        this.setAvailable(available);
        this.setAccrued(accrued);
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
	public void consolidateTransaction(AccruedTransaction accruedTransaction) {
		if(accruedTransaction.shouldBeConsolidate()){
			Double amountAccrued = accruedTransaction.getTrasaction().getOperationBankAccount().getAmount();
			setAvailable(getAvailable() + amountAccrued);
			setAccrued(getAccrued() - amountAccrued);
			accruedTransaction.setWasProcessed(new Boolean(true));
		}
	}

}
