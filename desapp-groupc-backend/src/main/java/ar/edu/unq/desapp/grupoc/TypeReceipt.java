package ar.edu.unq.desapp.grupoc;

public abstract class TypeReceipt {
    
    public void calculateDetail(Receipt receipt){
        receipt.setTaxed(0.0);
        receipt.setUntaxed(0.0);
        receipt.setIva(0.0);
    }
}
