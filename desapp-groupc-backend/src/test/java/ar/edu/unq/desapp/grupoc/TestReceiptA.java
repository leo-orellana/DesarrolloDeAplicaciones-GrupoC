package ar.edu.unq.desapp.grupoc;

import static org.mockito.Mockito.*;
import junit.framework.TestCase;

public class TestReceiptA extends TestCase {

    public void testCalculateDetailWithTotalBillAndNoTaxedValue(){
        Receipt mockReceipt = mock(Receipt.class);
        TypeA typeA = new TypeA();
        
        when(mockReceipt.getTaxed()).thenReturn(0.0);
        when(mockReceipt.getTotalBill()).thenReturn(1210.0);
        
        typeA.calculateDetail(mockReceipt);
        
        verify(mockReceipt, times(1)).setTaxed(1000.0);
        verify(mockReceipt, times(1)).setUntaxed(0.0);
        verify(mockReceipt, times(1)).setIva(210.0);
    }
    
    public void testCalculateDetailWithTotalBillAndTaxedValue(){
        Receipt mockReceipt = mock(Receipt.class);
        TypeA typeA = new TypeA();
        
        when(mockReceipt.getTaxed()).thenReturn(800.0);
        when(mockReceipt.getTotalBill()).thenReturn(968.0);
        
        typeA.calculateDetail(mockReceipt);
        
        verify(mockReceipt, times(1)).setUntaxed(0.0);
        verify(mockReceipt, times(1)).setIva(168.0);
    }
    
    public void testCalculateDetailWithTotalBillAndTaxedValueAndUntaxedValue(){
        Receipt mockReceipt = mock(Receipt.class);
        TypeA typeA = new TypeA();
        
        when(mockReceipt.getTaxed()).thenReturn(600.0);
        when(mockReceipt.getTotalBill()).thenReturn(800.0);
        
        typeA.calculateDetail(mockReceipt);
        
        verify(mockReceipt, times(1)).setUntaxed(74.0);
        verify(mockReceipt, times(1)).setIva(126.0);
    }
}
