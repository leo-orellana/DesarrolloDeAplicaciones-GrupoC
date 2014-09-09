package ar.edu.unq.desapp.grupoc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;

public class TestBankOperationDebit extends TestCase {

	public void testExecuteSendUpdateTheAvailableAndUpdateTheBalance()
	{
		AccountBank mockAccountBank = mock(AccountBank.class);
		OperationBankAccount mockOperationBankAccount = mock(OperationBankAccount.class);
		Movement mockMovement = mock(Movement.class);
		Double newAvailable = new Double(10);
		Double oldAvailable = new Double(8);
		Double amount = new Double(2);
		when(mockOperationBankAccount.getMovement()).thenReturn(mockMovement);
		when(mockMovement.processAmount(oldAvailable,amount)).thenReturn(newAvailable);
		when(mockAccountBank.getAvailable()).thenReturn(oldAvailable);
		when(mockOperationBankAccount.getAmount()).thenReturn(amount);
		BankOperationDebit bankOperationDebit = new BankOperationDebit();
		
		bankOperationDebit.execute(mockAccountBank, mockOperationBankAccount);
		
		verify(mockAccountBank).setAvailable(newAvailable);
		verify(mockOperationBankAccount).updateBalance(mockAccountBank);
	}
}
