package ar.edu.unq.desapp.grupoc.builders.test;

import ar.edu.unq.desapp.grupoc.model.BankOperation;
import ar.edu.unq.desapp.grupoc.model.Movement;
import ar.edu.unq.desapp.grupoc.model.OperationBankAccount;

public class BuilderOperationBankAccount {

    private Movement movement;
    private Double amount;
    private BankOperation bankOperation;
    private static BuilderOperationBankAccount instance;

    public static BuilderOperationBankAccount getInstance() {
        if (instance == null) {
            instance = new BuilderOperationBankAccount();
        }
        return instance;
    }

    public BuilderOperationBankAccount withMovement(Movement movement) {
        setMovement(movement);
        return this;
    }

    public BuilderOperationBankAccount withAmount(Double amount) {
        setAmount(amount);
        return this;
    }

    public BuilderOperationBankAccount withBankOperation(
            BankOperation bankOperation) {
        setBankOperation(bankOperation);
        return this;
    }

    public OperationBankAccount build() {
        return new OperationBankAccount(getMovement(), getAmount(),
                getBankOperation());
    }

    // ////////

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public BankOperation getBankOperation() {
        return bankOperation;
    }

    public void setBankOperation(BankOperation bankOperation) {
        this.bankOperation = bankOperation;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
