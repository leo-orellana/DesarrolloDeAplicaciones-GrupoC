package ar.edu.unq.desapp.grupoc.model.test;

import static org.mockito.Mockito.*;

import java.util.Date;

import ar.edu.unq.desapp.grupoc.builders.test.BuilderReceipt;
import ar.edu.unq.desapp.grupoc.model.Receipt;
import ar.edu.unq.desapp.grupoc.model.Supplier;
import ar.edu.unq.desapp.grupoc.model.TypeReceipt;
import junit.framework.TestCase;

public class TestReceipt extends TestCase {

    public void testConstructor() {
        TypeReceipt mockTypeReceipt = mock(TypeReceipt.class);
        Date mockDate = mock(Date.class);
        Supplier sup = new Supplier("name", "30-12345678-3");
        String concept = "concept";
        Double totalBill = 1210.0;
        Double taxed = 0.0;
        Double untaxed = 0.0;
        Double iva = 0.0;

        Receipt receipt = BuilderReceipt.getInstance()
                .withSupplier(sup).withConcept(concept).withDate(mockDate).withIva(iva)
                .withTaxed(taxed).withTotalBill(totalBill)
                .withTypeReceipt(mockTypeReceipt).withUntaxed(untaxed).build();
        
        assertSame(mockTypeReceipt, receipt.getTypeReceipt());
        assertSame(mockDate, receipt.getDate());
        assertSame(sup.getCompanyName(), receipt.getSupplier().getCompanyName());
        assertSame(sup.getCuit(), receipt.getSupplier().getCuit());
        assertSame(concept, receipt.getConcept());
        assertSame(totalBill, receipt.getTotalBill());
        assertEquals(taxed, receipt.getTaxed());
        assertSame(iva, receipt.getIva());
        assertSame(untaxed, receipt.getUntaxed());

    }
}
