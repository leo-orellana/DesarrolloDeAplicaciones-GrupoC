package ar.edu.unq.desapp.grupoc;

import static org.mockito.Mockito.*;
import junit.framework.TestCase;

public class TestAccount extends TestCase {

    public void testProcessOperation() {
        Operation mockOperation = mock(Operation.class);
        Account account = new Account();

        account.processOperation(mockOperation);

        verify(mockOperation, times(1)).execute(account);
    }
}
