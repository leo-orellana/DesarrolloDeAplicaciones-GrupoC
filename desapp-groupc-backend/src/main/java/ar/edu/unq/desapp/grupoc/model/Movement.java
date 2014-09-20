package ar.edu.unq.desapp.grupoc.model;

public abstract class Movement {

    /**
     * This method adds or subtracts the amount to total, depending who
     * implements it.
     * 
     * @param aTotal
     * @param aAmount
     * @return a new total
     */
    public abstract Double processAmount(Double total, Double amount);
}
