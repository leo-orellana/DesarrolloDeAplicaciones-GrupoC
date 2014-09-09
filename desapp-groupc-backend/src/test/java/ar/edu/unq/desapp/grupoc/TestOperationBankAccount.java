package ar.edu.unq.desapp.grupoc;

import ar.edu.unq.desapp.grupoc.builders.BuilderOperationBankAccount;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class TestOperationBankAccount extends TestCase {

    public void testExecuteCallExecuteInBankOperationWithAccountAndThis() {
        AccountBank mockAccount = mock(AccountBank.class);
        BankOperationDebit mockBankOperation = mock(BankOperationDebit.class);
        OperationBankAccount operationBankAccount = BuilderOperationBankAccount
                .getInstance().withBankOperation(mockBankOperation).build();

        operationBankAccount.execute(mockAccount);

        verify(mockBankOperation).execute(mockAccount, operationBankAccount);
    }

}
