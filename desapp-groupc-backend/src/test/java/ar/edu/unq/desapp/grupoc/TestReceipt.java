package ar.edu.unq.desapp.grupoc;

import static org.mockito.Mockito.*;

import java.util.Date;

import ar.edu.unq.desapp.grupoc.builders.BuilderReceipt;
import junit.framework.TestCase;

public class TestReceipt extends TestCase {

    public void testConstructor() {
        TypeReceipt mockTypeReceipt = mock(TypeReceipt.class);
        Date mockDate = mock(Date.class);
        String businessName = "name";
        String cuit = "30-12345678-3";
        String concept = "concept";
        Double totalBill = 1210.0;
        Double taxed = 1000.0;
        Double untaxed = 0.0;
        Double iva = 210.0;

        Receipt receipt = BuilderReceipt.getInstance()
                .withBusinessName(businessName).withConcept(concept)
                .withCuit(cuit).withDate(mockDate).withIva(iva)
                .withTaxed(taxed).withTotalBill(totalBill)
                .withTypeReceipt(mockTypeReceipt).withUntaxed(untaxed).build();
        
        assertSame(mockTypeReceipt, receipt.getTypeReceipt());
        assertSame(mockDate, receipt.getDate());
        assertSame(businessName, receipt.getBusinessName());
        assertSame(cuit, receipt.getCuit());
        assertSame(concept, receipt.getConcept());
        assertSame(totalBill, receipt.getTotalBill());
        assertSame(taxed, receipt.getTaxed());
        assertSame(iva, receipt.getIva());
        assertSame(untaxed, receipt.getUntaxed());

    }
}
