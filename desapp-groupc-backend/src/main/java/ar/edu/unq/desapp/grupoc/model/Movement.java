package ar.edu.unq.desapp.grupoc.model;

public abstract class Movement {

	private Integer id;
	private String name;
    
	public Movement(){}
	/**
     * This method adds or subtracts the amount to total, depending who
     * implements it.
     * 
     * @param aTotal
     * @param aAmount
     * @return a new total
     */
    public abstract Double processAmount(Double total, Double amount);
	
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public abstract boolean isIngress();
}
