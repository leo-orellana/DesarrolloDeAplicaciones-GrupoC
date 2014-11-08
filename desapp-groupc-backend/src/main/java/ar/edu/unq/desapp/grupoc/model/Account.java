package ar.edu.unq.desapp.grupoc.model;

public abstract class Account {

	private String name;
	private int id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract Operation getNewOperation(double amount, Movement movement, BankOperation bankOperation);
}
