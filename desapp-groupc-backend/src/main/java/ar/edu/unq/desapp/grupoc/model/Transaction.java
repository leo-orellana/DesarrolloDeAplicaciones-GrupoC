package ar.edu.unq.desapp.grupoc.model;

import java.util.Date;
import java.util.TimeZone;

public class Transaction {

	public Integer id;
	public Subcategory subcategory;
    public Time time;
    public String concept;
    public OperationCashAccount operationCashAccount;
    public OperationCheckingAccount operationCheckingAccount;
    public OperationBankAccount operationBankAccount;
    public Date date;
    
    public Transaction(){}
    
    public Transaction(Subcategory subcategory, Time time, String concept,
            OperationCashAccount operationCashAccount,
            OperationCheckingAccount operationCheckingAccount,
            OperationBankAccount operationBankAccount, Date date) {
        super();
        this.setSubcategory(subcategory);
        this.setTime(time);
        this.setConcept(concept);
        this.setOperationCashAccount(operationCashAccount);
        this.setOperationBankAccount(operationBankAccount);
        this.setOperationCheckingAccount(operationCheckingAccount);
        this.setDate(date);
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
}
