package ar.edu.unq.desapp.grupoc.model.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;
import ar.edu.unq.desapp.grupoc.builders.test.BuilderAccountManager;
import ar.edu.unq.desapp.grupoc.model.AccountBank;
import ar.edu.unq.desapp.grupoc.model.AccountCash;
import ar.edu.unq.desapp.grupoc.model.AccountChecking;
import ar.edu.unq.desapp.grupoc.model.AccountManager;
import ar.edu.unq.desapp.grupoc.model.OperationBankAccount;
import ar.edu.unq.desapp.grupoc.model.OperationCashAccount;
import ar.edu.unq.desapp.grupoc.model.OperationCheckingAccount;
import ar.edu.unq.desapp.grupoc.model.Transaction;

public class TestAccountManager extends TestCase {

	public void testInputTransactionSendMessagesToColaboratorsCorrectly() {

		AccountBank mockBank = mock(AccountBank.class);
		AccountCash mockCash = mock(AccountCash.class);
		AccountChecking mockChecking = mock(AccountChecking.class);
		Transaction mockTransaction = mock(Transaction.class);
		AccountManager mockAccountManager = new AccountManager(mockCash,
				mockChecking, mockBank);
		OperationBankAccount mockOperationBankAccount = mock(OperationBankAccount.class);
		OperationCashAccount mockOperationCashAccount = mock(OperationCashAccount.class);
		OperationCheckingAccount mockOperationCheckingAccount = mock(OperationCheckingAccount.class);

		when(mockTransaction.getOperationBankAccount()).thenReturn(
				mockOperationBankAccount);
		when(mockTransaction.getOperationCashAccount()).thenReturn(
				mockOperationCashAccount);
		when(mockTransaction.getOperationCheckingAccount()).thenReturn(
				mockOperationCheckingAccount);

		mockAccountManager.inputTransaction(mockTransaction);

		verify(mockBank, times(1)).processOperation(mockOperationBankAccount);
		verify(mockCash, times(1)).processOperation(mockOperationCashAccount);
		verify(mockChecking, times(1)).processOperation(
				mockOperationCheckingAccount);
		verify(mockTransaction, times(1)).getOperationBankAccount();
		verify(mockTransaction, times(1)).getOperationCashAccount();
		verify(mockTransaction, times(1)).getOperationCheckingAccount();
	}

	public void testGetCashBalance() {
		AccountCash mockCash = mock(AccountCash.class);
		AccountManager mockAccountManager = BuilderAccountManager.getInstance()
				.withAccountCash(mockCash).build();

		mockAccountManager.getCashBalance();

		verify(mockCash, times(1)).getBalance();
	}

	public void testGetBankBalance() {
		AccountBank mockBank = mock(AccountBank.class);
		AccountManager mockAccountManager = BuilderAccountManager.getInstance()
				.withAccountBank(mockBank).build();

		mockAccountManager.getBankBalance();

		verify(mockBank, times(1)).getBalance();
	}

	public void testGetCheckingAccountBalance() {
		AccountChecking mockChecking = mock(AccountChecking.class);
		AccountManager mockAccountManager = BuilderAccountManager.getInstance()
				.withAccountChecking(mockChecking).build();

		mockAccountManager.getCheckingBalance();

		verify(mockChecking, times(1)).getBalance();
	}

	public void testGetAvailableMoney() {
		AccountBank mockBank = mock(AccountBank.class);
		AccountManager mockAccountManager = BuilderAccountManager.getInstance()
				.withAccountBank(mockBank).build();

		mockAccountManager.getAvailableMoney();

		verify(mockBank, times(1)).getAvailable();
	}

	public void testGetAccruedMoney() {
		AccountBank mockBank = mock(AccountBank.class);
		AccountManager mockAccountManager = BuilderAccountManager.getInstance()
				.withAccountBank(mockBank).build();

		mockAccountManager.getAccruedMoney();

		verify(mockBank, times(1)).getAccrued();
	}

	public void testConsolidateAccountMockTransaction2Valid_MockTransaction1Invalid(){
    	Transaction mockTransaction1 = mock(Transaction.class);
    	Transaction mockTransaction2 = mock(Transaction.class);
    	
    	when(mockTransaction1.getWasConsolidated()).thenReturn(true);
    	when(mockTransaction1.getShouldBeConsolidated()).thenReturn(false);
    	when(mockTransaction1.getDate()).thenReturn(new Date("2014/11/1"));
    	when(mockTransaction2.getWasConsolidated()).thenReturn(false);
    	when(mockTransaction2.getShouldBeConsolidated()).thenReturn(true);
    	when(mockTransaction2.getDate()).thenReturn(new Date("2014/11/1"));
    	    	
    	List<Transaction> transactions = new ArrayList<Transaction>();
    	transactions.add(mockTransaction1);
    	transactions.add(mockTransaction2);
    	
    	AccountBank mockAccountBank = mock(AccountBank.class);	
    	AccountManager accountManager = BuilderAccountManager.getInstance().withAccountBank(mockAccountBank).build();
    	
    	accountManager.consolidateAccount(transactions);
    	
    	verify(mockAccountBank, times(0)).consolidateTransaction(mockTransaction1);
    	verify(mockAccountBank, times(1)).consolidateTransaction(mockTransaction2);
    }
}
