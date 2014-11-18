package ar.edu.unq.desapp.grupoc.model.test;

import ar.edu.unq.desapp.grupoc.model.Account;
import ar.edu.unq.desapp.grupoc.model.Movement;
import ar.edu.unq.desapp.grupoc.model.Operation;
import ar.edu.unq.desapp.grupoc.model.OperationCashAccount;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class TestOperation extends TestCase {

    public void testExecuteCallUpdateBalanceAndSendSetBalanceToAccountWithTheReturnOfProcessAmount() {
        Account mockAccount = mock(Account.class);
        Movement mockMovement = mock(Movement.class);
        Double amount = new Double(2);
        Double oldBalance = new Double(10);
        Double newBalance = new Double(12);
        Operation operation = new OperationCashAccount(mockMovement, amount);
        when(mockMovement.processAmount(oldBalance, amount)).thenReturn(
                newBalance);
        when(mockAccount.getBalance()).thenReturn(oldBalance);

        operation.execute(mockAccount);

        verify(mockAccount).setBalance(newBalance);
    }

}
