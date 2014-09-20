package ar.edu.unq.desapp.grupoc.model.test;

import ar.edu.unq.desapp.grupoc.builders.test.BuilderOperationBankAccount;
import ar.edu.unq.desapp.grupoc.model.AccountBank;
import ar.edu.unq.desapp.grupoc.model.BankOperationDebit;
import ar.edu.unq.desapp.grupoc.model.OperationBankAccount;
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
