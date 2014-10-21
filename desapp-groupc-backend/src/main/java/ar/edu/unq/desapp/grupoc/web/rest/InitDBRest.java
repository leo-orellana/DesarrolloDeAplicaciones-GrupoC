package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.BankOperation;
import ar.edu.unq.desapp.grupoc.model.BankOperationCredit;
import ar.edu.unq.desapp.grupoc.model.Category;
import ar.edu.unq.desapp.grupoc.model.Egress;
import ar.edu.unq.desapp.grupoc.model.Ingress;
import ar.edu.unq.desapp.grupoc.model.Movement;
import ar.edu.unq.desapp.grupoc.model.OperationBankAccount;
import ar.edu.unq.desapp.grupoc.model.Subcategory;
import ar.edu.unq.desapp.grupoc.model.Time;
import ar.edu.unq.desapp.grupoc.model.Transaction;
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
		
		// CREATE TRANSACTIONS
		Transaction transaction1 = new Transaction();
		transaction1.setConcept("transaction example");
		transaction1.setDate(new Date(2014, 10, 20));
		transaction1.setTime(Time.Morning);
		transaction1.setSubcategory(pagoSueldos);
		BankOperation bankOp = new BankOperationCredit();
		transaction1.setOperationBankAccount(new OperationBankAccount(ingress, new Double(10), bankOp));
		
		getTransactionService().save(transaction1);
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
}
