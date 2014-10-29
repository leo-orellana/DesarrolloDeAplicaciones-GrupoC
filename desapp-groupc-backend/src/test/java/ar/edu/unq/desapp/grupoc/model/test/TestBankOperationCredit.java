package ar.edu.unq.desapp.grupoc.model.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;
import ar.edu.unq.desapp.grupoc.model.AccountBank;
import ar.edu.unq.desapp.grupoc.model.BankOperationCredit;
import ar.edu.unq.desapp.grupoc.model.Egress;
import ar.edu.unq.desapp.grupoc.model.Ingress;
import ar.edu.unq.desapp.grupoc.model.OperationBankAccount;

public class TestBankOperationCredit extends TestCase {

    public void testExecuteSendUpdateTheAccruedWhithAIngressCreditAndUpdateTheBalance() {
        AccountBank mockAccountBank = mock(AccountBank.class);
        OperationBankAccount mockOperationBankAccount = mock(OperationBankAccount.class);
        Ingress mockMovement = mock(Ingress.class);
        Double newAccrued = new Double(10);
        Double oldAccrued = new Double(8);
        Double amount = new Double(2);
        when(mockMovement.isIngress()).thenReturn(true);
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
    
    public void testExecuteSendUpdateTheAvailableWhithAEgressCreditAndUpdateTheBalance() {
        AccountBank mockAccountBank = mock(AccountBank.class);
        OperationBankAccount mockOperationBankAccount = mock(OperationBankAccount.class);
        Egress mockMovement = mock(Egress.class);
        Double newAvailable = new Double(10);
        Double oldAvailable = new Double(8);
        Double amount = new Double(2);
        when(mockMovement.isIngress()).thenReturn(false);
        when(mockOperationBankAccount.getMovement()).thenReturn(mockMovement);
        when(mockMovement.processAmount(oldAvailable, amount)).thenReturn(
                newAvailable);
        when(mockAccountBank.getAvailable()).thenReturn(8.0);
        when(mockOperationBankAccount.getAmount()).thenReturn(2.0);
        BankOperationCredit bankOperationCredit = new BankOperationCredit();

        bankOperationCredit.execute(mockAccountBank, mockOperationBankAccount);

        verify(mockAccountBank).setAvailable(newAvailable);
        verify(mockOperationBankAccount).updateBalance(mockAccountBank);
    }
    
    
    
    public void testIsCreditReturnTrue(){
    	BankOperationCredit operation = new BankOperationCredit();
    	
    	assertTrue(operation.isCredit());
    }
}
