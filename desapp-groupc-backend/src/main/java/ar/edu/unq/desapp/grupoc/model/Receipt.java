package ar.edu.unq.desapp.grupoc.model;

import java.util.Date;

public class Receipt {

    private Date date;
    private TypeReceipt typeReceipt;
    private String businessName;
    private String cuit;
    private String concept;
    private Double totalBill;
    private Double taxed;
    private Double untaxed;
    private Double iva;

    public Receipt(Date date, TypeReceipt typeReceipt, String businessName,
            String cuit, String concept, Double totalBill, Double taxed,
            Double untaxed, Double iva) {
        super();
        this.setDate(date);
        this.setTypeReceipt(typeReceipt);
        this.setBusinessName(businessName);
        this.setCuit(cuit);
        this.setConcept(concept);
        this.setTotalBill(totalBill);
        this.setTaxed(taxed);
        this.setUntaxed(untaxed);
        this.setIva(iva);
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

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
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
        this.typeReceipt.calculateDetail(this);
    }

    public Double getTaxed() {
        return taxed;
    }

    public void setTaxed(Double taxed) {
        this.taxed = taxed;
        this.typeReceipt.calculateDetail(this);
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
