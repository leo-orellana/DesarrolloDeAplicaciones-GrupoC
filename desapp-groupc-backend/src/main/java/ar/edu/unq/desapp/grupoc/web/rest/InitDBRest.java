package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.Date;

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
import ar.edu.unq.desapp.grupoc.model.OperationBankAccount;
import ar.edu.unq.desapp.grupoc.model.OperationCashAccount;
import ar.edu.unq.desapp.grupoc.model.OperationCheckingAccount;
import ar.edu.unq.desapp.grupoc.model.Receipt;
import ar.edu.unq.desapp.grupoc.model.Subcategory;
import ar.edu.unq.desapp.grupoc.model.Supplier;
import ar.edu.unq.desapp.grupoc.model.Time;
import ar.edu.unq.desapp.grupoc.model.Transaction;
import ar.edu.unq.desapp.grupoc.model.TypeA;
import ar.edu.unq.desapp.grupoc.model.TypeB;
import ar.edu.unq.desapp.grupoc.model.TypeC;
import ar.edu.unq.desapp.grupoc.model.TypeX;
import ar.edu.unq.desapp.grupoc.services.AccountManagerService;
import ar.edu.unq.desapp.grupoc.services.AccountService;
import ar.edu.unq.desapp.grupoc.services.BankOperationService;
import ar.edu.unq.desapp.grupoc.services.CategoryService;
import ar.edu.unq.desapp.grupoc.services.MovementService;
import ar.edu.unq.desapp.grupoc.services.ReceiptService;
import ar.edu.unq.desapp.grupoc.services.SubCategoryService;
import ar.edu.unq.desapp.grupoc.services.SupplierService;
import ar.edu.unq.desapp.grupoc.services.TransactionService;
import ar.edu.unq.desapp.grupoc.services.TypeReceiptService;

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
	private TypeReceiptService typeReceiptService;
	private ReceiptService receiptService;
	
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
		
		Category compras = new Category();
		compras.setName("Compras");
		compras.setMovement(egress);

		getCategoryService().save(pagos);
		getCategoryService().save(ventas);
		getCategoryService().save(compras);
		
		
		// CREATE SUBCATEGORIES
		Subcategory pagoSueldos = new Subcategory("Pago Sueldos", pagos);
		Subcategory televisores = new Subcategory("Venta televisores", ventas);
		Subcategory compraDeSoftware = new Subcategory("Compra de software", compras);
		Subcategory compraDeAutos = new Subcategory("Compra de autos", compras);
		
		getSubcategoryService().save(pagoSueldos);
		getSubcategoryService().save(televisores);
		getSubcategoryService().save(compraDeSoftware);
		getSubcategoryService().save(compraDeAutos);
		
		
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
		
		// CREATE ACCOUNT MANAGER
		
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
		
		// CREATE TYPE_RECEIPTS
		
		TypeA typeA = new TypeA();
		TypeB typeB = new TypeB();
		TypeC typeC = new TypeC();
		TypeX typeX = new TypeX();
		
		getTypeReceiptService().save(typeA);
		getTypeReceiptService().save(typeB);
		getTypeReceiptService().save(typeC);
		getTypeReceiptService().save(typeX);
		
		//CREATE TRANSACTIONS AND RECEIPT
		
		OperationCashAccount operationCashAccount = new OperationCashAccount(ingress, 4000.0);
		OperationCheckingAccount operationCheckingAccount = new OperationCheckingAccount(ingress, 0.0);
		OperationBankAccount operationBankAccount = new OperationBankAccount(ingress, 0.0, bankOpDebit);
		Transaction transaction1 = new Transaction(1, televisores, Time.Afternoon, "Venta TV Philips", operationCashAccount, operationCheckingAccount, operationBankAccount, new Date());
		
		operationCashAccount.setConsolidateProperties(transaction1);
		accountManager.inputTransaction(transaction1);
		
		OperationCashAccount operationCashAccount2 = new OperationCashAccount(ingress, 5000.0);
		OperationCheckingAccount operationCheckingAccount2 = new OperationCheckingAccount(ingress, 0.0);
		OperationBankAccount operationBankAccount2 = new OperationBankAccount(ingress, 0.0, bankOpDebit);
		Transaction transaction2 = new Transaction(2, televisores, Time.Afternoon, "Venta TV Samsung", operationCashAccount2, operationCheckingAccount2, operationBankAccount2, new Date());
		
		operationCashAccount2.setConsolidateProperties(transaction2);
		accountManager.inputTransaction(transaction2);
		
		OperationCashAccount operationCashAccount3 = new OperationCashAccount(egress, 1410.0);
		OperationCheckingAccount operationCheckingAccount3 = new OperationCheckingAccount(egress, 0.0);
		OperationBankAccount operationBankAccount3 = new OperationBankAccount(egress, 0.0, bankOpDebit);
		Transaction transaction3 = new Transaction(3, compraDeSoftware, Time.Afternoon, "Software de facturación", operationCashAccount3, operationCheckingAccount3, operationBankAccount3, new Date());
	
		operationCashAccount3.setConsolidateProperties(transaction3);
		accountManager.inputTransaction(transaction3);
		
		Receipt receipt = new Receipt(new Date(), typeA, "Morellato S.A", "20-36987655-3", "Software de facturación", 1410.0, 0.0, 200.0, 0.0);
		
		transaction3.setReceipt(receipt);
		
		OperationCashAccount operationCashAccount4 = new OperationCashAccount(egress, 70000.0);
		OperationCheckingAccount operationCheckingAccount4 = new OperationCheckingAccount(egress, 0.0);
		OperationBankAccount operationBankAccount4 = new OperationBankAccount(egress, 0.0, bankOpDebit);
		Transaction transaction4 = new Transaction(4, compraDeAutos, Time.Afternoon, "Compra Chevrolet Corsa", operationCashAccount4, operationCheckingAccount4, operationBankAccount4, new Date());
		
		operationCashAccount4.setConsolidateProperties(transaction4);
		accountManager.inputTransaction(transaction4);
		
		Receipt receipt2 = new Receipt(new Date(), typeC, "Gonzalía Asociados", "20-32543678-7", "Compra Chevrolet Corsa", 70000.0, 0.0, 0.0, 0.0);
		
		transaction4.setReceipt(receipt2);
		
		getTransactionService().save(transaction1);
		getTransactionService().save(transaction2);
		getTransactionService().save(transaction3);
		getTransactionService().save(transaction4);
		
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

	public TypeReceiptService getTypeReceiptService() {
		return typeReceiptService;
	}

	public void setTypeReceiptService(TypeReceiptService typeReceiptService) {
		this.typeReceiptService = typeReceiptService;
	}

	public ReceiptService getReceiptService() {
		return receiptService;
	}

	public void setReceiptService(ReceiptService receiptService) {
		this.receiptService = receiptService;
	}
}
