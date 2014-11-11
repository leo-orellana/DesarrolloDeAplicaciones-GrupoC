package ar.edu.unq.desapp.grupoc.web.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.AccountManager;
import ar.edu.unq.desapp.grupoc.model.BankOperationCredit;
import ar.edu.unq.desapp.grupoc.model.Ingress;
import ar.edu.unq.desapp.grupoc.model.Operation;
import ar.edu.unq.desapp.grupoc.model.OperationBankAccount;
import ar.edu.unq.desapp.grupoc.model.OperationCashAccount;
import ar.edu.unq.desapp.grupoc.model.OperationCheckingAccount;
import ar.edu.unq.desapp.grupoc.model.Subcategory;
import ar.edu.unq.desapp.grupoc.model.Time;
import ar.edu.unq.desapp.grupoc.model.Transaction;
import ar.edu.unq.desapp.grupoc.services.AccountManagerService;
import ar.edu.unq.desapp.grupoc.services.AccountService;
import ar.edu.unq.desapp.grupoc.services.BankOperationService;
import ar.edu.unq.desapp.grupoc.services.CategoryService;
import ar.edu.unq.desapp.grupoc.services.SubCategoryService;
import ar.edu.unq.desapp.grupoc.services.TransactionService;

@Service
@Path("/transactionService")
public class TransactionRest {

	private TransactionService transactionService;
	private SubCategoryService subCategoryService;
	private CategoryService categoryService;
	private AccountService accountService;
	private BankOperationService bankOperationService;
	private AccountManagerService accountManagerService;

	@GET
	@Path("/transaction/{id}")
	@Produces("application/json")
	public Transaction getTransaction(@PathParam("id") final int id) {
		Transaction tran = getTransactionService().getById(id);
		return tran;
	}

	@GET
	@Path("/delete/{id}")
	@Produces("application/json")
	public Transaction deleteTransaction(@PathParam("id") final int id) {
		Transaction tran = this.getTransaction(id);
		getTransactionService().delete(tran);
		return tran;
	}

	@GET
	@Path("/transactions")
	@Produces("application/json")
	public List<Transaction> getTransactions() {
		return getTransactionService().retriveAll();
	}

	@GET
	@Path("/filterByName/{name}")
	@Produces("application/json")
	public List<Transaction> filterByConcept(
			@PathParam("concept") final String concept) {
		return getTransactionService().filterByConcept(concept);
	}

	@GET
	@Path("/save/{date}/{idSubcategory}/{concept}/{time}/{numOperation}/{idAccount}/{idBankOperation}/{amount}")
	@Produces("application/json")
	public Transaction saveTransaction(@PathParam("date") final String date,
			@PathParam("idSubcategory") final int idSubcategory,
			@PathParam("concept") final String concept,
			@PathParam("time") final String time,
			@PathParam("numOperation") final int numOperation,
			@PathParam("idAccount") final int idAccount,
			@PathParam("idBankOperation") final int idBankOperation,
			@PathParam("amount") final int amount) throws ParseException {
		Subcategory subcategory = getSubCategoryService()
				.getById(idSubcategory);
		Time t = Time.valueOf(time);
		Operation operation = getAccountService().getById(idAccount)
				.getNewOperation(amount,
						subcategory.getCategory().getMovement(),
						getBankOperationService().getById(idBankOperation));
		OperationCashAccount operationCash = new OperationCashAccount(
				new Ingress(), new Double(0));
		OperationCheckingAccount operationChecking = new OperationCheckingAccount(
				new Ingress(), new Double(0));
		OperationBankAccount operationBank = new OperationBankAccount(
				new Ingress(), new Double(0), new BankOperationCredit());
		if (operation.getClass() == OperationBankAccount.class) {
			operationBank = (OperationBankAccount) operation;
		} else {
			if (operation.getClass() == OperationCashAccount.class) {
				operationCash = (OperationCashAccount) operation;
			} else {
				operationChecking = (OperationCheckingAccount) operation;
			}
		}

		Date javaDate = new SimpleDateFormat("yy-MM-dd").parse(date);

		AccountManager accMan = getAccountManagerService().retriveAll().get(0);
		Transaction trans = new Transaction(subcategory, t, concept,
				operationCash, operationChecking, operationBank, javaDate);
		accMan.inputTransaction(trans);
		trans.setAmountAccruedBank(accMan.getAccruedMoney());
		trans.setAmountAvailableBank(accMan.getAvailableMoney());
		trans.setAmountOfCashAccount(accMan.getCashBalance());
		trans.setAmountOfCheckingAccount(accMan.getCheckingBalance());
		getAccountManagerService().save(accMan);
		getTransactionService().save(trans);

		return trans;
	}

	public TransactionService getTransactionService() {
		return transactionService;
	}

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	public SubCategoryService getSubCategoryService() {
		return subCategoryService;
	}

	public void setSubCategoryService(SubCategoryService subCategoryService) {
		this.subCategoryService = subCategoryService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public BankOperationService getBankOperationService() {
		return bankOperationService;
	}

	public void setBankOperationService(
			BankOperationService bankOperationService) {
		this.bankOperationService = bankOperationService;
	}

	public AccountManagerService getAccountManagerService() {
		return accountManagerService;
	}

	public void setAccountManagerService(
			AccountManagerService accountManagerService) {
		this.accountManagerService = accountManagerService;
	}

}
