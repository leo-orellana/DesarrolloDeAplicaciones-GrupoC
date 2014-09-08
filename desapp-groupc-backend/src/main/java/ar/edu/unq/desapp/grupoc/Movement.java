package ar.edu.unq.desapp.grupoc;

public abstract class Movement {

	/**
	 * This method adds or subtracts the amount to total, depending who implements it.
	 * @param aTotal
	 * @param aAmount
	 * @return a new total
	 */
	public abstract Integer processAmount(Integer aTotal, Integer aAmount);
}
