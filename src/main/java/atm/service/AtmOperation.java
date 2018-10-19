package atm.service;

import atm.model.Account;
import atm.model.Atm;
import atm.model.Card;
import atm.persisting.impl.AtmDataBaseImpl;
import atm.persisting.impl.utils.MySQLConnector;
import org.apache.log4j.Logger;

public class AtmOperation {
    final static Logger logger=Logger.getLogger(AtmOperation.class);
    AccountOperation accountOperation=new AccountOperation();
    CardOperation cardOperation=new CardOperation();
    HistoryOperation historyOperation = new HistoryOperation();
    AtmDataBaseImpl atmDataBase = new AtmDataBaseImpl();


    Atm atm=atmDataBase.getAtm(1);
    Card card;
    Account account;

    private int atmUah=atmDataBase.getAtm(atm.getId()).getUah();
    public static int countFalsePin=0;
    public static boolean checkCard=false;
    public static boolean checkPin=false;



    public boolean checkCard(int id){
        if((cardOperation.checkCardDate(id)==true)&&(cardOperation.checkCardBlock(id)==true)){
            card=cardOperation.getCard(id);
            return true;
        }
        return false;
    }

    public boolean checkCardPin(String pin){
        if(checkPinOneTime(pin)==false) {
            if (countFalsePin > 2) {
                historyOperation.addHistory("Wrong pin 3 times");
                cardOperation.blockCard(1, historyOperation.getLastHistory());
                returnCard();
                return false;
            } else {
                return false;
            }
        }else{
            account=accountOperation.getAccount(card.getAccountId());
            return true;
        }
    }

    private boolean checkPinOneTime(String pin){
        if(cardOperation.checkCardPin(card.getId(), pin)==true){
            countFalsePin=0; return true;
        }
        countFalsePin++; return false;
    }



    public void transfer(int toAccount, int amount){
        accountOperation.transfer(account.getId(), toAccount, amount);
    }

    public int showBalance(){
        return accountOperation.getBalance(account.getId());
    }

    public void withdraw(int amount){
        if(atm.getUah()>amount){
            accountOperation.withdraw(account.getId(), amount);
            atm.withdraw(amount);
//            atmUah=atmUah-amount;
            atmDataBase.updateAtm(atm);
            historyOperation.addHistory("Withdraw cash "+amount);
            logger.info(historyOperation.getLastHistory());
        }else{
            historyOperation.addHistory("Withdraw cash canceled");
            logger.error(historyOperation.getLastHistory());
        }
    }

    public void returnCard(){
        account=null;
        card=null;
        checkPin=false;
        checkCard=false;
        countFalsePin=0;
    }

    public int getAtmUah() {
        return atmUah;
    }

    // try with singlton
    private static AtmOperation atmOperation;

    private AtmOperation(){
        atmDataBase.setMySQLConnector(new MySQLConnector());
    }

    public static AtmOperation getAtmOperation(){

        if(atmOperation==null){
            atmOperation=new AtmOperation();
        }
        return atmOperation;
    }



}
