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

import ar.edu.unq.desapp.grupoc.model.Receipt;
import ar.edu.unq.desapp.grupoc.model.Supplier;
import ar.edu.unq.desapp.grupoc.model.Transaction;
import ar.edu.unq.desapp.grupoc.model.TypeReceipt;
import ar.edu.unq.desapp.grupoc.services.AccountManagerService;
import ar.edu.unq.desapp.grupoc.services.AccountService;
import ar.edu.unq.desapp.grupoc.services.BankOperationService;
import ar.edu.unq.desapp.grupoc.services.CategoryService;
import ar.edu.unq.desapp.grupoc.services.ReceiptService;
import ar.edu.unq.desapp.grupoc.services.SubCategoryService;
import ar.edu.unq.desapp.grupoc.services.SupplierService;
import ar.edu.unq.desapp.grupoc.services.TransactionService;
import ar.edu.unq.desapp.grupoc.services.TypeReceiptService;

@Service
@Path("/receiptService")
public class ReceiptRest {

	private TransactionService transactionService;
	private SubCategoryService subCategoryService;
	private CategoryService categoryService;
	private AccountService accountService;
	private BankOperationService bankOperationService;
	private AccountManagerService accountManagerService;
	private ReceiptService receiptService;
	private TypeReceiptService typeReceiptService;
	private SupplierService supplierService;
	private TransactionRest transactionRest;

	@GET
	@Path("/receipt/{id}")
	@Produces("application/json")
	public Receipt getReceipt(@PathParam("id") final int id) {
		Receipt rec = getReceiptService().getById(id);
		return rec;
	}

	@GET
	@Path("/delete/{id}")
	@Produces("application/json")
	public Receipt deleteReceipt(@PathParam("id") final int id) {
		Receipt rec = this.getReceipt(id);
		getReceiptService().delete(rec);
		return rec;
	}

	@GET
	@Path("/receipts")
	@Produces("application/json")
	public List<Receipt> getReceipts() {
		return getReceiptService().retriveAll();
	}

	@GET
	@Path("/filterByName/{name}")
	@Produces("application/json")
	public List<Receipt> filterByConcept(
			@PathParam("concept") final String concept) {
		return getReceiptService().filterByConcept(concept);
	}

	@GET
	@Path("/save/{date}/{idTypeReceipt}/{idSupplier}/{concept}/{totalBill}/{taxed}/{untaxed}/{iva}/{idSubcategory}/{time}/{numOperation}/{idAccount}/{idBankOperation}")
	@Produces("application/json")
	public Receipt saveReceipt(
			@PathParam("date") final String date,
			@PathParam("idTypeReceipt") final int idTypeReceipt,
			@PathParam("idSupplier") final int idSupplier,
			@PathParam("concept") final String concept,
			@PathParam("totalBill") final Double totalBill,
			@PathParam("taxed") final Double taxed,
			@PathParam("untaxed") final Double untaxed,
			@PathParam("iva") final Double iva,
			@PathParam("idSubcategory") final int idSubcategory,
			@PathParam("time") final String time,
			@PathParam("numOperation") final int numOperation,
			@PathParam("idAccount") final int idAccount,
			@PathParam("idBankOperation") final int idBankOperation) throws ParseException {

		TypeReceipt typeReceipt = getTypeReceiptService().getById(idTypeReceipt);
		Supplier supplier = getSupplierService().getById(idSupplier);
		Date javaDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		
		Receipt receipt = new Receipt(javaDate, typeReceipt, supplier, concept, totalBill, taxed, untaxed, iva);

		Transaction trans = getTransactionRest().saveTransaction(date,
				idSubcategory, concept, time, numOperation, idAccount,
				idBankOperation, totalBill);

		trans.setReceipt(receipt);

		getTransactionService().update(trans);

		return receipt;
	}

	@GET
	@Path("/update/{idReceipt}/{idSupplier}/{concept}/{time}/{numOperation}")
	@Produces("application/json")
	public Receipt updateReceipt(
			@PathParam("idReceipt") final int idReceipt,
			@PathParam("idSupplier") final int idSupplier,
			@PathParam("concept") final String concept,
			@PathParam("time") final String time,
			@PathParam("numOperation") final int numOperation) throws ParseException {

		Supplier supplier = getSupplierService().getById(idSupplier);
		
		Receipt receipt = getReceiptService().getById(idReceipt);
		receipt.setSupplier(supplier);
		receipt.setConcept(concept);

		Transaction trans = getTransactionService().getByReceiptId(idReceipt);
		
		getTransactionRest().updateTransaction(trans.getId(), concept, time, numOperation);
		getReceiptService().update(receipt);
		
		return receipt;
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

	public ReceiptService getReceiptService() {
		return receiptService;
	}

	public void setReceiptService(ReceiptService receiptService) {
		this.receiptService = receiptService;
	}

	public TransactionRest getTransactionRest() {
		return transactionRest;
	}

	public void setTransactionRest(TransactionRest transactionRest) {
		this.transactionRest = transactionRest;
	}

	public TypeReceiptService getTypeReceiptService() {
		return typeReceiptService;
	}

	public void setTypeReceiptService(TypeReceiptService typeReceiptService) {
		this.typeReceiptService = typeReceiptService;
	}

	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
}
