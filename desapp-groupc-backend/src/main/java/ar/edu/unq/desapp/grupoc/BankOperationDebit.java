package ar.edu.unq.desapp.grupoc;

public class BankOperationDebit extends BankOperation {

    /**
     * This method updated a available money and a balance of the AccountBank
     */
    @Override
    public void execute(AccountBank account,
            OperationBankAccount operationBankAccount) {
        account.setAvailable(operationBankAccount.getMovement().processAmount(
                account.getAvailable(), operationBankAccount.getAmount()));
        operationBankAccount.updateBalance(account);
    }

}
