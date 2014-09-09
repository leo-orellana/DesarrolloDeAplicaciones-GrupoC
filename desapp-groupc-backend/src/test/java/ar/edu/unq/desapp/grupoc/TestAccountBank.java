package ar.edu.unq.desapp.grupoc;

import junit.framework.TestCase;

import static org.mockito.Mockito.*;

public class TestAccountBank extends TestCase {

	public void testConsolidateTransactionWhenSouldBeConsolidateSetAvailableSetAccruedAndSetWasProcessedInTrue(){
		AccruedTransaction mockAccruedTransaction = mock(AccruedTransaction.class);
		when(mockAccruedTransaction.shouldBeConsolidate()).thenReturn(new Boolean(true));
		Transaction mockTransaction = mock(Transaction.class);
		when(mockAccruedTransaction.getTrasaction()).thenReturn(mockTransaction);
		OperationBankAccount mockOperationBankAccount = mock(OperationBankAccount.class);
		when(mockTransaction.getOperationBankAccount()).thenReturn(mockOperationBankAccount);
		when(mockOperationBankAccount.getAmount()).thenReturn(new Double(2));
		AccountBank accountBank = new AccountBank(new Double(10), new Double(10));
		
		accountBank.consolidateTransaction(mockAccruedTransaction);
		
		assertEquals(accountBank.getAvailable(), new Double(12));
		assertEquals(accountBank.getAccrued(), new Double(8));
	}
}
