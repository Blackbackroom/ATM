package atm.service;

import atm.model.Account;
import atm.persisting.impl.AccountDataBaseImpl;
import org.apache.log4j.Logger;

public class AccountOperation {
    final static Logger logger=Logger.getLogger(AccountOperation.class);
    AccountDataBaseImpl accountDataBase=new AccountDataBaseImpl();
    HistoryOperation historyOperation=new HistoryOperation();
    Account account;

    public Account getAccount(int id){
        return accountDataBase.getAccount(id);
    }

    public int getBalance(int id){
        return getAccount(id).getUah();
    }

    private boolean checkBalance(int id, int amount){
        return getAccount(id).getUah()>amount;
    }

    public void withdraw(int id, int amount){
        account=getAccount(id);
        if(checkBalance(id,amount)==true) {
            account.setUah(account.getUah() - amount);
            accountDataBase.updateAccount(account);
            historyOperation.addHistory("Withdraw from account "+amount);
            logger.info(historyOperation.getLastHistory());
        }else {
            historyOperation.addHistory("Withdraw canceled");
            logger.error(historyOperation.getLastHistory());
        }
    }

    private void deposit(int id, int amount){
        account=getAccount(id);
        account.setUah(account.getUah()+amount);
        accountDataBase.updateAccount(account);
        historyOperation.addHistory("Deposit to account"+amount);
        logger.info(historyOperation.getLastHistory());
    }

    public void transfer(int idFrom, int idTo, int amount){
        if(checkBalance(idFrom,amount)==true){
            historyOperation.addHistory("Transfer to "+idTo+" "+amount);
            logger.info(historyOperation.getLastHistory());
        withdraw(idFrom, amount);
        deposit(idTo, amount);
        }else {
            historyOperation.addHistory("Transfer canceled");
            logger.error(historyOperation.getLastHistory());
        }
    }




}
