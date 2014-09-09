package ar.edu.unq.desapp.grupoc;

import junit.framework.TestCase;

import static org.mockito.Mockito.*;

public class TestOperation extends TestCase {

    public void testExecuteCallUpdateBalanceAndSendSetBalanceToAccountWithTheReturnOfProcessAmount() {
        Account mockAccount = mock(Account.class);
        Movement mockMovement = mock(Movement.class);
        Double amount = new Double(2);
        Double oldBalance = new Double(10);
        Double newBalance = new Double(12);
        Operation operation = new Operation(mockMovement, amount);
        when(mockMovement.processAmount(oldBalance, amount)).thenReturn(
                newBalance);
        when(mockAccount.getBalance()).thenReturn(oldBalance);

        operation.execute(mockAccount);

        verify(mockAccount).setBalance(newBalance);
    }

}
