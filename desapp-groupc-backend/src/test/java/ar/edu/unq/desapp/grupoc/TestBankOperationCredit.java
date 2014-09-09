package ar.edu.unq.desapp.grupoc;

import junit.framework.TestCase;

import static org.mockito.Mockito.*;

public class TestBankOperationCredit extends TestCase {

    public void testExecuteSendUpdateTheAccruedAndUpdateTheBalance() {
        AccountBank mockAccountBank = mock(AccountBank.class);
        OperationBankAccount mockOperationBankAccount = mock(OperationBankAccount.class);
        Movement mockMovement = mock(Movement.class);
        Double newAccrued = new Double(10);
        Double oldAccrued = new Double(8);
        Double amount = new Double(2);
        when(mockOperationBankAccount.getMovement()).thenReturn(mockMovement);
        when(mockMovement.processAmount(oldAccrued, amount)).thenReturn(
                newAccrued);
        when(mockAccountBank.getAccrued()).thenReturn(8.0);
        when(mockOperationBankAccount.getAmount()).thenReturn(2.0);
        BankOperationCredit bankOperationCredit = new BankOperationCredit();

        bankOperationCredit.execute(mockAccountBank, mockOperationBankAccount);

        verify(mockAccountBank).setAccrued(newAccrued);
        verify(mockOperationBankAccount).updateBalance(mockAccountBank);
    }
    
    public void testIsCreditReturnTrue(){
    	BankOperationCredit operation = new BankOperationCredit();
    	
    	assertTrue(operation.isCredit());
    }
}
