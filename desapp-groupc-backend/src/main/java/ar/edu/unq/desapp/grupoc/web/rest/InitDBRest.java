package ar.edu.unq.desapp.grupoc.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.AccountBank;
import ar.edu.unq.desapp.grupoc.model.AccountCash;
import ar.edu.unq.desapp.grupoc.model.AccountChecking;
import ar.edu.unq.desapp.grupoc.model.AccountManager;
import ar.edu.unq.desapp.grupoc.model.BankOperationCredit;
import ar.edu.unq.desapp.grupoc.model.BankOperationDebit;
import ar.edu.unq.desapp.grupoc.model.Category;
import ar.edu.unq.desapp.grupoc.model.Egress;
import ar.edu.unq.desapp.grupoc.model.Ingress;
import ar.edu.unq.desapp.grupoc.model.Subcategory;
import ar.edu.unq.desapp.grupoc.model.Supplier;
import ar.edu.unq.desapp.grupoc.services.AccountManagerService;
import ar.edu.unq.desapp.grupoc.services.AccountService;
import ar.edu.unq.desapp.grupoc.services.BankOperationService;
import ar.edu.unq.desapp.grupoc.services.CategoryService;
import ar.edu.unq.desapp.grupoc.services.MovementService;
import ar.edu.unq.desapp.grupoc.services.SubCategoryService;
import ar.edu.unq.desapp.grupoc.services.SupplierService;
import ar.edu.unq.desapp.grupoc.services.TransactionService;

@Service
@Path("/db")
public class InitDBRest {

	private SubCategoryService subcategoryService;
	private CategoryService categoryService;
	private MovementService movementService;
	private TransactionService transactionService;
	private AccountManagerService accountManagerService;
	private AccountService accountService;
	private BankOperationService bankOperationService;
	private SupplierService supplierService;
	
	@GET
	@Path("/init")
	public void initDB() {
		
		// CREATE MOVEMENTS
		Ingress ingress = new Ingress();
		ingress.setName("Ingress");

		Egress egress = new Egress();
		egress.setName("Egress");
		
		getMovementService().save(ingress);
		getMovementService().save(egress);

		
		// CREATE CATEGORIES
		Category pagos = new Category();
		pagos.setName("Pago");
		pagos.setMovement(egress);
		
		Category ventas = new Category();
		ventas.setName("Ventas");
		ventas.setMovement(ingress);
		
		getCategoryService().save(pagos);
		getCategoryService().save(ventas);
		
		
		// CREATE SUBCATEGORIES
		Subcategory pagoSueldos = new Subcategory("Pago Sueldos", pagos);
		Subcategory televisores = new Subcategory("Venta televisores", ventas);
		
		getSubcategoryService().save(pagoSueldos);
		getSubcategoryService().save(televisores);
		
		
		// CREATE ACCOUNTS
		AccountCash accountCash = new AccountCash();
		accountCash.setBalance(new Double(0));
		
		AccountChecking accountChecking = new AccountChecking();
		accountChecking.setBalance(new Double(0));
		
		AccountBank accountBank = new AccountBank();
		accountBank.setBalance(new Double(0));
		accountBank.setAccrued(new Double(0));
		accountBank.setAvailable(new Double(0));
		
		getAccountService().save(accountCash);
		getAccountService().save(accountChecking);
		getAccountService().save(accountBank);
		
		// CREATE BANK OPERATIONS
		BankOperationCredit bankOpCredit = new BankOperationCredit();
		BankOperationDebit bankOpDebit = new BankOperationDebit();
		
		getBankOperationService().save(bankOpDebit);
		getBankOperationService().save(bankOpCredit);
		
		// CREATE ACCOUN MANAGER
		
		AccountManager accountManager = new AccountManager(accountCash,accountChecking,accountBank);
		
		getAccountManagerService().save(accountManager);
		
		// CREATE SUPPLIERS
		
		Supplier cocaCola = new Supplier("Coca cola", "20-31345675-3");
		Supplier fargo = new Supplier("Fargo", "30-34562453-1");
		Supplier philips = new Supplier("Philips", "23-33512345-3");
		Supplier samsung = new Supplier("Samsung", "23-45697645-5");
		
		getSupplierService().save(cocaCola);
		getSupplierService().save(fargo);
		getSupplierService().save(philips);
		getSupplierService().save(samsung);
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

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public BankOperationService getBankOperationService() {
		return bankOperationService;
	}

	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public void setBankOperationService(BankOperationService bankOperationService) {
		this.bankOperationService = bankOperationService;
	}
}
