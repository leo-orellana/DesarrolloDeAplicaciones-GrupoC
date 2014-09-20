package ar.edu.unq.desapp.grupoc.model.test;

import static org.mockito.Mockito.*;
import ar.edu.unq.desapp.grupoc.model.Receipt;
import ar.edu.unq.desapp.grupoc.model.TypeB;
import junit.framework.TestCase;

public class TestTypeReceiptB extends TestCase {

    public void testCalculateDetail(){
        Receipt mockReceipt = mock(Receipt.class);
        TypeB typeB = new TypeB();
        
        typeB.calculateDetail(mockReceipt);
        
        verify(mockReceipt, times(1)).setTaxed(0.0);
        verify(mockReceipt, times(1)).setUntaxed(0.0);
        verify(mockReceipt, times(1)).setIva(0.0);
    }
}
