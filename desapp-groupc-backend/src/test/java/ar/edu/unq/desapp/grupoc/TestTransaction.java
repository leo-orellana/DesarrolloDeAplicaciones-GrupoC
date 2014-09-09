package ar.edu.unq.desapp.grupoc;

import static org.mockito.Mockito.mock;

import java.util.Date;

import junit.framework.TestCase;

public class TestTransaction extends TestCase {

    public void testConstructor() {
        Subcategory mockSubcategory = mock(Subcategory.class);
        OperationCashAccount mockOperationCashAccount = mock(OperationCashAccount.class);
        OperationCheckingAccount mockOperationCheckingAccount = mock(OperationCheckingAccount.class);
        OperationBankAccount mockOperationBankAccount = mock(OperationBankAccount.class);
        Date mockDate = mock(Date.class);

        Transaction transaction = new Transaction(mockSubcategory,
                Time.Afternoon, "concept", mockOperationCashAccount,
                mockOperationCheckingAccount, mockOperationBankAccount,
                mockDate);

        assertEquals(mockSubcategory, transaction.getSubcategory());
        assertEquals(mockOperationCashAccount,
                transaction.getOperationCashAccount());
        assertEquals(mockOperationCheckingAccount,
                transaction.getOperationCheckingAccount());
        assertEquals(mockOperationBankAccount,
                transaction.getOperationBankAccount());
        assertEquals(mockDate, transaction.getDate());
    }
}
