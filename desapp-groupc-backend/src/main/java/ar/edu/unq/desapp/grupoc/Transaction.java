package ar.edu.unq.desapp.grupoc;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class Transaction {

	private Subcategory subcategory;
	private Time time;
	private String concept;
	private OperationCashAccount operationCashAccount;
	private OperationCheckingAccount operationCheckingAccount;
	private OperationBankAccount operationBankAccount;
	private Date date;
	public Subcategory getSubcategory() {
		return subcategory;
	}
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
	public void setOperationCashAccount(OperationCashAccount operationCashAccount) {
		this.operationCashAccount = operationCashAccount;
	}
	public OperationCheckingAccount getOperationCheckingAccount() {
		return operationCheckingAccount;
	}
	public void setOperationCheckingAccount(OperationCheckingAccount operationCheckingAccount) {
		this.operationCheckingAccount = operationCheckingAccount;
	}
	public OperationBankAccount getOperationBankAccount() {
		return operationBankAccount;
	}
	public void setOperationBankAccount(OperationBankAccount operationBankAccount) {
		this.operationBankAccount = operationBankAccount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
