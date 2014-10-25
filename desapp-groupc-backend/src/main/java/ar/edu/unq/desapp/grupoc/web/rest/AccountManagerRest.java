package ar.edu.unq.desapp.grupoc.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.AccountManager;
import ar.edu.unq.desapp.grupoc.services.AccountManagerService;

@Service
@Path("/accountManagerService")
public class AccountManagerRest {

	AccountManagerService accountManagerService;
	
	@GET
	@Path("/accountManager")
	@Produces("application/json")
	public List<AccountManager> getCategories() {
		return getAccountManagerService().retriveAll();
	}

	public AccountManagerService getAccountManagerService() {
		return accountManagerService;
	}

	public void setAccountManagerService(AccountManagerService accountManagerService) {
		this.accountManagerService = accountManagerService;
	}

	
}
