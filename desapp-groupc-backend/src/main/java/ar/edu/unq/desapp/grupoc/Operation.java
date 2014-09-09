package ar.edu.unq.desapp.grupoc;

public class Operation {

	private Movement movement;
	private Double amount;
	
	public Operation(Movement movement, Double amount) {
		super();
		setMovement(movement);
		setAmount(amount);
	}

	/**
	 * This method updated a balance of the aAccount.
	 * @param aAccount
	 */
	public void execute(Account aAccount){
		this.updateBalance(aAccount);
	}
	
	public void updateBalance(Account aAccount) {
		aAccount.setBalance(this.getMovement().processAmount(aAccount.getBalance(),this.amount));
	}
	
	//////////


	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Movement getMovement() {
		return movement;
	}

	public void setMovement(Movement movement) {
		this.movement = movement;
	}
	
}
