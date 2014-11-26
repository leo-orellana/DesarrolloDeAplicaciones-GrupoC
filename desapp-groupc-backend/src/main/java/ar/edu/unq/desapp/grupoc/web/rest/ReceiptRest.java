package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.Receipt;
import ar.edu.unq.desapp.grupoc.services.AccountManagerService;
import ar.edu.unq.desapp.grupoc.services.AccountService;
import ar.edu.unq.desapp.grupoc.services.BankOperationService;
import ar.edu.unq.desapp.grupoc.services.CategoryService;
import ar.edu.unq.desapp.grupoc.services.ReceiptService;
import ar.edu.unq.desapp.grupoc.services.SubCategoryService;
import ar.edu.unq.desapp.grupoc.services.TransactionService;

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
	public void setBankOperationService(BankOperationService bankOperationService) {
		this.bankOperationService = bankOperationService;
	}
	public AccountManagerService getAccountManagerService() {
		return accountManagerService;
	}
	public void setAccountManagerService(AccountManagerService accountManagerService) {
		this.accountManagerService = accountManagerService;
	}
	public ReceiptService getReceiptService() {
		return receiptService;
	}
	public void setReceiptService(ReceiptService receiptService) {
		this.receiptService = receiptService;
	}
}
