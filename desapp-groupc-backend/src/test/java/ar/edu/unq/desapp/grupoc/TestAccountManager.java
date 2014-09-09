package ar.edu.unq.desapp.grupoc;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.unq.desapp.grupoc.builders.BuilderAccountManager;
import junit.framework.TestCase;

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

        mockAccountManager.getCheckingAccountBalance();

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
    
    public void testDeleteConsolidationTransactionRemoveOneTransaction()
    {
    	ServiceDataBase mockServiceDB = mock(ServiceDataBase.class);
    	AccruedTransaction mockAccruedTransactionTrue = mock(AccruedTransaction.class);
    	AccruedTransaction mockAccruedTransactionFalse = mock(AccruedTransaction.class);
    	
    	when(mockAccruedTransactionTrue.wasProcessed()).thenReturn(new Boolean(true));
    	when(mockAccruedTransactionFalse.wasProcessed()).thenReturn(new Boolean(false));
    	List<AccruedTransaction> transactions = new ArrayList<AccruedTransaction>(
    			Arrays.asList(mockAccruedTransactionFalse,mockAccruedTransactionTrue));
    	AccountManager accountManager = BuilderAccountManager.getInstance().build();
    	List<AccruedTransaction> newTransactions = new ArrayList<AccruedTransaction>();
  
    	accountManager.deleteConsolidateTransactions(mockServiceDB,transactions,newTransactions);
    	
    	assertTrue(newTransactions.contains(mockAccruedTransactionFalse));
    	assertFalse(newTransactions.contains(mockAccruedTransactionTrue));
    }
    
    public void testConsolidationAccrued(){
    	AccountBank mockAccountBank = mock(AccountBank.class);
    	AccountManager accountManager = BuilderAccountManager.getInstance().withAccountBank(mockAccountBank).build();
    	AccruedTransaction mockAccruedTransactionTrue = mock(AccruedTransaction.class);
    	AccruedTransaction mockAccruedTransactionFalse = mock(AccruedTransaction.class);
    	List<AccruedTransaction> transactions = new ArrayList<AccruedTransaction>(
    			Arrays.asList(mockAccruedTransactionFalse,mockAccruedTransactionTrue));
    	
    	accountManager.consolidateAccrued(transactions);
    	
    	verify(mockAccountBank).consolidateTransaction(mockAccruedTransactionFalse);
    	verify(mockAccountBank).consolidateTransaction(mockAccruedTransactionTrue);
    }

}
