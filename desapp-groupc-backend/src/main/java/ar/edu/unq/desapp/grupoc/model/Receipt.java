package ar.edu.unq.desapp.grupoc.model;

import java.util.Date;

public class Receipt {

	private int id;
	private Date date;
	private TypeReceipt typeReceipt;
	private Supplier supplier;
	private String concept;
	private Double totalBill;
	private Double taxed;
	private Double untaxed;
	private Double iva;

	public Receipt() {
	}

	public Receipt(Date date, TypeReceipt typeReceipt, Supplier supplier, String concept,
			Double totalBill, Double taxed, Double untaxed, Double iva) {
		super();
		this.setDate(date);
		this.setTypeReceipt(typeReceipt);
		this.setSupplier(supplier);
		this.setConcept(concept);
		this.setUntaxed(untaxed);
		this.setIva(iva);
		this.calculateDetails(totalBill, taxed);
	}

	private void calculateDetails(Double totalBill, Double taxed) {
		this.taxed = taxed;
		this.totalBill = totalBill;
		this.taxed = this.typeReceipt.calculateDetail(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public TypeReceipt getTypeReceipt() {
		return typeReceipt;
	}

	public void setTypeReceipt(TypeReceipt typeReceipt) {
		this.typeReceipt = typeReceipt;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public Double getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(Double totalBill) {
		this.totalBill = totalBill;
		this.taxed = this.getTypeReceipt().calculateDetail(this);
	}

	public Double getTaxed() {
		return taxed;
	}

	public void setTaxed(Double taxed) {
		this.taxed = taxed;
		this.taxed = this.getTypeReceipt().calculateDetail(this);
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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}
