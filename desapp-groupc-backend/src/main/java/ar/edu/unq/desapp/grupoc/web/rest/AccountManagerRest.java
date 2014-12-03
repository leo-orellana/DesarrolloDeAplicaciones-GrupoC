package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.AccountManager;
import ar.edu.unq.desapp.grupoc.model.Transaction;
import ar.edu.unq.desapp.grupoc.services.AccountManagerService;
import ar.edu.unq.desapp.grupoc.services.TransactionService;

@Service
@Path("/accountManagerService")
public class AccountManagerRest {

	AccountManagerService accountManagerService;
	TransactionService transactionService;
	
	@GET
	@Path("/accountManager")
	@Produces("application/json")
	public AccountManager getAccount() {
		List<AccountManager> accounts = getAccountManagerService().retriveAll();
		return accounts.get(0);
	}

	@GET
	@Path("/consolidateAccount")
	@Produces("application/json")
	public Transaction consolidate(){
		List<Transaction> transactions = getTransactionService().retriveAll();
		AccountManager accountManager = getAccount();
		List<Transaction> consolidateds = accountManager.consolidateAccount(transactions);
		
		Transaction transaction = new Transaction(null, null, "Consolidation", null, null, null, new Date());
		transaction.setAmountAccruedBank(accountManager.getAccruedMoney());
		transaction.setAmountAvailableBank(accountManager.getAvailableMoney());
		transaction.setAmountOfCashAccount(accountManager.getCashBalance());
		transaction.setAmountOfCheckingAccount(accountManager.getCheckingBalance());
		transaction.setWasConsolidated(true);
		transaction.setShouldBeConsolidated(false);
		
		getAccountManagerService().save(accountManager);
		getTransactionService().save(transaction);
		
		for (Transaction t : consolidateds) {
			getTransactionService().update(t);
		}
		
		return transaction;
	}
	
	public AccountManagerService getAccountManagerService() {
		return accountManagerService;
	}

	public void setAccountManagerService(AccountManagerService accountManagerService) {
		this.accountManagerService = accountManagerService;
	}

	public TransactionService getTransactionService() {
		return transactionService;
	}

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
}
