package ar.edu.unq.desapp.grupoc;

public class Account {

	private Integer balance;
	
	/**
	 * 
	 * @param aOperation
	 * This method calls operation eject and he is a parameter.
	 */
	public void processOperation(Operation aOperation)
	{
		aOperation.execute(this);
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	
}
