package ar.edu.unq.desapp.grupoc;

public class Operation {

	private Movement movement;
	private Integer amount;
	
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


	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Movement getMovement() {
		return movement;
	}

	public void setMovement(Movement movement) {
		this.movement = movement;
	}
	
}
