package ar.edu.unq.desapp.grupoc;

public class AccountBank extends Account{

	private Double available;
	private Double accrued;
	
	public AccountBank(Double available, Double accrued) {
		super();
		this.setAvailable(available);
		this.setAccrued(accrued);
	}
	
	//////////
	
	public Double getAvailable() {
		return available;
	}
	public void setAvailable(Double available) {
		this.available = available;
	}
	public Double getAccrued() {
		return accrued;
	}
	public void setAccrued(Double accrued) {
		this.accrued = accrued;
	}
	
}
