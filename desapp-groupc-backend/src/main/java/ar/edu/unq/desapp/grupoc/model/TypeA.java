package ar.edu.unq.desapp.grupoc.model;

public class TypeA extends TypeReceipt {

    private Double ivaPercentage = 0.21;

    public void calculateDetail(Receipt receipt) {
        Double taxed = receipt.getTaxed();
        Double iva = 0.0;

        if (taxed == 0.0) {
            taxed = receipt.getTotalBill() / 1.21;
            receipt.setTaxed(taxed);
        }
        iva = taxed * this.getIvaPercentage();
        receipt.setUntaxed(receipt.getTotalBill() - taxed - iva);
        receipt.setIva(iva);
    }

    public Double getIvaPercentage() {
        return ivaPercentage;
    }

    public void setIvaPercentage(Double ivaPercentage) {
        this.ivaPercentage = ivaPercentage;
    }
}
