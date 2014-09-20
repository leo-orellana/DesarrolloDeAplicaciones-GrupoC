package ar.edu.unq.desapp.grupoc.model;

public class Account {

    private Double balance;

    /**
     * 
     * @param aOperation
     *            This method calls operation eject and he is a parameter.
     */
    public void processOperation(Operation aOperation) {
        aOperation.execute(this);
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
