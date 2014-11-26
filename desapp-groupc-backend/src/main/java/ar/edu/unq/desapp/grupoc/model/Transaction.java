package ar.edu.unq.desapp.grupoc.model;

import java.util.Date;

public class Transaction {

	public Integer id;
	public Subcategory subcategory;
	public Time time;
	public String concept;
	public OperationCashAccount operationCashAccount;
	public OperationCheckingAccount operationCheckingAccount;
	public OperationBankAccount operationBankAccount;
	public Date date;
	public int numOperation;
	public Boolean shouldBeConsolidated;
	public Boolean wasConsolidated;
	public Receipt receipt;
	public boolean hasReceipt;

	// this amounts are the values of the accounts in the input transaction's
	// moment
	public double amountOfCashAccount;
	public double amountOfCheckingAccount;
	public double amountAvailableBank;
	public double amountAccruedBank;

	public Transaction() {
	}

	public Transaction(int numOperation, Subcategory subcategory, Time time,
			String concept, OperationCashAccount operationCashAccount,
			OperationCheckingAccount operationCheckingAccount,
			OperationBankAccount operationBankAccount, Date date) {
		super();
		this.setNumOperation(numOperation);
		this.setAmountAccruedBank(amountAccruedBank);
		this.setAmountAvailableBank(amountAvailableBank);
		this.setAmountOfCashAccount(amountOfCashAccount);
		this.setAmountOfCheckingAccount(amountOfCheckingAccount);
		this.setSubcategory(subcategory);
		this.setTime(time);
		this.setConcept(concept);
		this.setOperationCashAccount(operationCashAccount);
		this.setOperationBankAccount(operationBankAccount);
		this.setOperationCheckingAccount(operationCheckingAccount);
		this.setDate(date);
		this.setHasReceipt(false);
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	// ////////

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public OperationCashAccount getOperationCashAccount() {
		return operationCashAccount;
	}

	public void setOperationCashAccount(
			OperationCashAccount operationCashAccount) {
		this.operationCashAccount = operationCashAccount;
	}

	public OperationCheckingAccount getOperationCheckingAccount() {
		return operationCheckingAccount;
	}

	public void setOperationCheckingAccount(
			OperationCheckingAccount operationCheckingAccount) {
		this.operationCheckingAccount = operationCheckingAccount;
	}

	public OperationBankAccount getOperationBankAccount() {
		return operationBankAccount;
	}

	public void setOperationBankAccount(
			OperationBankAccount operationBankAccount) {
		this.operationBankAccount = operationBankAccount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getAmountOfCashAccount() {
		return amountOfCashAccount;
	}

	public void setAmountOfCashAccount(double amountOfCashAccount) {
		this.amountOfCashAccount = amountOfCashAccount;
	}

	public double getAmountOfCheckingAccount() {
		return amountOfCheckingAccount;
	}

	public void setAmountOfCheckingAccount(double amountOfCheckingAccount) {
		this.amountOfCheckingAccount = amountOfCheckingAccount;
	}

	public double getAmountAvailableBank() {
		return amountAvailableBank;
	}

	public void setAmountAvailableBank(double amountAvailableBank) {
		this.amountAvailableBank = amountAvailableBank;
	}

	public double getAmountAccruedBank() {
		return amountAccruedBank;
	}

	public void setAmountAccruedBank(double amountAccruedBank) {
		this.amountAccruedBank = amountAccruedBank;
	}

	public double getAmountBankAccount() {
		return getAmountAccruedBank() + getAmountAvailableBank();
	}

	public int getNumOperation() {
		return numOperation;
	}

	public void setNumOperation(int numOperation) {
		this.numOperation = numOperation;
	}

	public Boolean getShouldBeConsolidated() {
		return shouldBeConsolidated;
	}

	public void setShouldBeConsolidated(Boolean shouldBeConsolidated) {
		this.shouldBeConsolidated = shouldBeConsolidated;
	}

	public Boolean getWasConsolidated() {
		return wasConsolidated;
	}

	public void setWasConsolidated(Boolean wasConsolidated) {
		this.wasConsolidated = wasConsolidated;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
		this.setHasReceipt(true);
	}

	public boolean isHasReceipt() {
		return hasReceipt;
	}

	public void setHasReceipt(boolean hasReceipt) {
		this.hasReceipt = hasReceipt;
	}
}
