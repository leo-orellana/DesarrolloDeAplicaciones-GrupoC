package ar.edu.unq.desapp.grupoc.builders.test;

import java.util.Date;

import ar.edu.unq.desapp.grupoc.model.Receipt;
import ar.edu.unq.desapp.grupoc.model.TypeReceipt;

public class BuilderReceipt {

    private Date date;
    private TypeReceipt typeReceipt;
    private String businessName;
    private String cuit;
    private String concept;
    private Double totalBill;
    private Double taxed;
    private Double untaxed;
    private Double iva;
    private static BuilderReceipt instance;

    private BuilderReceipt() {
    }

    public static BuilderReceipt getInstance() {
        if (instance == null) {
            instance = new BuilderReceipt();
        }
        return instance;
    }
    public BuilderReceipt withDate(Date date) {
        this.setDate(date);
        return this;
    }

    public BuilderReceipt withTypeReceipt(TypeReceipt typeReceipt) {
        this.setTypeReceipt(typeReceipt);
        return this;
    }

    public BuilderReceipt withBusinessName(String businessName) {
        this.setBusinessName(businessName);
        return this;
    }

    public BuilderReceipt withCuit(String cuit) {
        this.setCuit(cuit);
        return this;
    }

    public BuilderReceipt withConcept(String concept) {
        this.setConcept(concept);
        return this;
    }

    public BuilderReceipt withTotalBill(Double totalBill) {
        this.setTotalBill(totalBill);
        return this;
    }

    public BuilderReceipt withTaxed(Double taxed) {
        this.setTaxed(taxed);
        return this;
    }

    public BuilderReceipt withUntaxed(Double untaxed){
        this.setUntaxed(untaxed);
        return this;
    }

    public BuilderReceipt withIva(Double iva) {
        this.setIva(iva);
        return this;
    }

    public Receipt build() {
        return new Receipt(getDate(), getTypeReceipt(), getBusinessName(),
                getCuit(), getConcept(), getTotalBill(), getTaxed(),
                getUntaxed(), getIva());
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
