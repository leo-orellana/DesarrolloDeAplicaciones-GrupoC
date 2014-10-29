package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.AccountBank;
import ar.edu.unq.desapp.grupoc.model.AccountCash;
import ar.edu.unq.desapp.grupoc.model.AccountChecking;
import ar.edu.unq.desapp.grupoc.model.AccountManager;
import ar.edu.unq.desapp.grupoc.model.BankOperation;
import ar.edu.unq.desapp.grupoc.model.BankOperationCredit;
import ar.edu.unq.desapp.grupoc.model.BankOperationDebit;
import ar.edu.unq.desapp.grupoc.model.Category;
import ar.edu.unq.desapp.grupoc.model.Egress;
import ar.edu.unq.desapp.grupoc.model.Ingress;
import ar.edu.unq.desapp.grupoc.model.OperationBankAccount;
import ar.edu.unq.desapp.grupoc.model.OperationCashAccount;
import ar.edu.unq.desapp.grupoc.model.OperationCheckingAccount;
import ar.edu.unq.desapp.grupoc.model.Subcategory;
import ar.edu.unq.desapp.grupoc.model.Time;
import ar.edu.unq.desapp.grupoc.model.Transaction;
import ar.edu.unq.desapp.grupoc.services.AccountManagerService;
import ar.edu.unq.desapp.grupoc.services.CategoryService;
import ar.edu.unq.desapp.grupoc.services.MovementService;
import ar.edu.unq.desapp.grupoc.services.SubCategoryService;
import ar.edu.unq.desapp.grupoc.services.TransactionService;

@Service
@Path("/db")
public class InitDBRest {

	private SubCategoryService subcategoryService;
	private CategoryService categoryService;
	private MovementService movementService;
	private TransactionService transactionService;
	private AccountManagerService accountManagerService;
	
	@GET
	@Path("/init")
	public void initDB() {
		
		// CREATE MOVEMENTS
		Ingress ingress = new Ingress();
		ingress.setName("Ingress");

		Egress egress = new Egress();
		egress.setName("Egress");

		
		// CREATE CATEGORIES
		Category pagos = new Category();
		pagos.setName("Pago");
		pagos.setMovement(egress);
		
		Category ventas = new Category();
		ventas.setName("Ventas");
		ventas.setMovement(ingress);
		
		
		// CREATE SUBCATEGORIES
		Subcategory pagoSueldos = new Subcategory("Pago Sueldos", pagos);
		Subcategory televisores = new Subcategory("Venta televisores", ventas);
		
		
		// ACCOUNT MANAGER
		AccountCash accountCash = new AccountCash();
		accountCash.setBalance(new Double(0));
		
		AccountChecking accountChecking = new AccountChecking();
		accountChecking.setBalance(new Double(0));
		
		AccountBank accountBank = new AccountBank();
		accountBank.setBalance(new Double(0));
		accountBank.setAccrued(new Double(0));
		accountBank.setAvailable(new Double(0));
		
		AccountManager accountManager = new AccountManager(accountCash,accountChecking,accountBank);
		
		// CREATE TRANSACTIONS
		Transaction transaction = new Transaction();
		transaction.setConcept("transaction example");
		transaction.setDate(new Date(2014, 10, 20));
		transaction.setTime(Time.Morning);
		transaction.setSubcategory(pagoSueldos);
		transaction.setOperationBankAccount(new OperationBankAccount(egress, new Double(10), new BankOperationCredit()));
		transaction.setOperationCashAccount(new OperationCashAccount(egress,new Double(15)));
		transaction.setOperationCheckingAccount(new OperationCheckingAccount(egress,new Double(20)));

		
		accountManager.inputTransaction(transaction);
		
		getSubcategoryService().save(pagoSueldos);
		getSubcategoryService().save(televisores);
		
		getCategoryService().save(pagos);
		getCategoryService().save(ventas);
		
		getMovementService().save(ingress);
		getMovementService().save(egress);
		
		getTransactionService().save(transaction);
		
		getAccountManagerService().save(accountManager);
		
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public MovementService getMovementService() {
		return movementService;
	}

	public void setMovementService(MovementService movementService) {
		this.movementService = movementService;
	}
	public SubCategoryService getSubcategoryService() {
		return subcategoryService;
	}
	
	public void setSubcategoryService(SubCategoryService subcategoryService) {
		this.subcategoryService = subcategoryService;
	}

	public TransactionService getTransactionService() {
		return transactionService;
	}

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	public AccountManagerService getAccountManagerService() {
		return accountManagerService;
	}

	public void setAccountManagerService(AccountManagerService accountManagerService) {
		this.accountManagerService = accountManagerService;
	}
}
