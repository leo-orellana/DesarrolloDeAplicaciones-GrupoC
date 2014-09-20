package ar.edu.unq.desapp.grupoc.model;

public class AccruedTransaction {

	private Boolean transactionProcess;
	private Transaction trasaction;
	
	
	public AccruedTransaction(Transaction trasaction) {
		super();
		this.transactionProcess = new Boolean(false);
		this.trasaction = trasaction;
	}
	
	public Boolean wasProcessed(){
		return getTransactionProcess();
	}
	
	//////////
	
	public Boolean getTransactionProcess() {
		return transactionProcess;
	}
	public void setTransactionProcess(Boolean transactionProcess) {
		this.transactionProcess = transactionProcess;
	}
	public Transaction getTrasaction() {
		return trasaction;
	}
	public void setTrasaction(Transaction trasaction) {
		this.trasaction = trasaction;
	}

	/**
	 * This method indicate if the transaction is a bank operation and is a credit operation.
	 * @return
	 */
	public Boolean shouldBeConsolidate() {
		return ((getTrasaction().getOperationBankAccount() != null) && 
				(getTrasaction().getOperationBankAccount().getBankOperation().isCredit()));
	}

	public void setWasProcessed(Boolean bool) {
		this.setTransactionProcess(bool);
	}
	
}
