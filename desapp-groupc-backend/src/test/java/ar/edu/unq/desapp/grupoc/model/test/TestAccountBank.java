package ar.edu.unq.desapp.grupoc.model.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;
import ar.edu.unq.desapp.grupoc.model.AccountBank;
import ar.edu.unq.desapp.grupoc.model.OperationBankAccount;
import ar.edu.unq.desapp.grupoc.model.Transaction;

public class TestAccountBank extends TestCase {

	public void testConsolidateTransactionWhenSouldBeConsolidateSetAvailableSetAccruedAndSetWasProcessedInTrue(){
		Transaction mockTransaction = mock(Transaction.class);
		OperationBankAccount mockOperationBankAccount = mock(OperationBankAccount.class);
		when(mockTransaction.getOperationBankAccount()).thenReturn(mockOperationBankAccount);
		when(mockOperationBankAccount.getAmount()).thenReturn(new Double(2));
		AccountBank accountBank = new AccountBank(new Double(10), new Double(10));
		
		accountBank.consolidateTransaction(mockTransaction);
		
		assertEquals(accountBank.getAvailable(), new Double(12));
		assertEquals(accountBank.getAccrued(), new Double(8));
		verify(mockTransaction, times(1)).setWasConsolidated(true);
	}
}
