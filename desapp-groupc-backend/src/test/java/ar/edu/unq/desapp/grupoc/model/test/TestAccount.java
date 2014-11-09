package ar.edu.unq.desapp.grupoc.model.test;

import static org.mockito.Mockito.*;
import ar.edu.unq.desapp.grupoc.model.Account;
import ar.edu.unq.desapp.grupoc.model.AccountCash;
import ar.edu.unq.desapp.grupoc.model.Operation;
import junit.framework.TestCase;

public class TestAccount extends TestCase {

    public void testProcessOperation() {
        Operation mockOperation = mock(Operation.class);
        Account account = new AccountCash();

        account.processOperation(mockOperation);

        verify(mockOperation, times(1)).execute(account);
    }
}
