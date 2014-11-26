package ar.edu.unq.desapp.grupoc.model;

public class Detail {

	private Double taxed;
	private Double untaxed;
	private Double iva;
	
	public Detail(){}
	
	public Detail(Double taxed, Double untaxed, Double iva) {
		super();
		this.taxed = taxed;
		this.untaxed = untaxed;
		this.iva = iva;
	}

	public Double getTaxed() {
		return taxed;
	}
	public void setTaxed(Double taxed) {
		this.taxed = taxed;
	}
	public Double getUntaxed() {
		return untaxed;
	}
	public void setUntaxed(Double untaxed) {
		this.untaxed = untaxed;
	}
	public Double getIva() {
		return iva;
	}
	public void setIva(Double iva) {
		this.iva = iva;
	}
	
	
}
