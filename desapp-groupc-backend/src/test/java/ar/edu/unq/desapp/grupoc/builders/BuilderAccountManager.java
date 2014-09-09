package ar.edu.unq.desapp.grupoc.builders;

import ar.edu.unq.desapp.grupoc.AccountBank;
import ar.edu.unq.desapp.grupoc.AccountCash;
import ar.edu.unq.desapp.grupoc.AccountChecking;
import ar.edu.unq.desapp.grupoc.AccountManager;

public class BuilderAccountManager {

    private AccountCash accountCash;
    private AccountChecking accountChecking;
    private AccountBank accountBank;
    private static BuilderAccountManager instance;

    private BuilderAccountManager() {
    }

    public static BuilderAccountManager getInstance() {
        if (instance == null) {
            instance = new BuilderAccountManager();
        }
        return instance;
    }

    public BuilderAccountManager withAccountCash(AccountCash accountCash) {
        this.setAccountCash(accountCash);
        return this;
    }

    public BuilderAccountManager withAccountBank(AccountBank accountBank) {
        this.setAccountBank(accountBank);
        return this;
    }

    public BuilderAccountManager withAccountChecking(
            AccountChecking accountChecking) {
        this.setAccountChecking(accountChecking);
        return this;
    }

    public AccountManager build() {
        return new AccountManager(getAccountCash(), getAccountChecking(),
                getAccountBank());
    }

    public AccountCash getAccountCash() {
        return accountCash;
    }

    public void setAccountCash(AccountCash accountCash) {
        this.accountCash = accountCash;
    }

    public AccountChecking getAccountChecking() {
        return accountChecking;
    }

    public void setAccountChecking(AccountChecking accountChecking) {
        this.accountChecking = accountChecking;
    }

    public AccountBank getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(AccountBank accountBank) {
        this.accountBank = accountBank;
    }

}
