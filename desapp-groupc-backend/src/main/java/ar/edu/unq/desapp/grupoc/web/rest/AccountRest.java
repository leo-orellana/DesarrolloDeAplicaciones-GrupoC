package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.Account;
import ar.edu.unq.desapp.grupoc.services.AccountService;

@Service
@Path("/accountService")
public class AccountRest {

	private AccountService accountService;
	@GET
	@Path("/accounts")
	@Produces("application/json")
	public List<Account> getAccounts() {
		return getAccountService().retriveAll();
	}
	
	public AccountService getAccountService() {
		return accountService;
	}
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

}
