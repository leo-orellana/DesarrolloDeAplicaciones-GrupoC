package ar.edu.unq.desapp.grupoc;

public class AccountBank extends Account{

	private Integer available;
	private Integer accrued;
	
	public AccountBank(Integer available, Integer accrued) {
		super();
		this.setAvailable(available);
		this.setAccrued(accrued);
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	public Integer getAccrued() {
		return accrued;
	}
	public void setAccrued(Integer accrued) {
		this.accrued = accrued;
	}
	
}
