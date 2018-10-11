package atm.service;

import atm.persisting.impl.AccountDataBaseImpl;
import atm.persisting.impl.AtmDataBaseImpl;
import atm.persisting.impl.CardDataBaseImpl;
import atm.persisting.impl.HistoryDataBaseImpl;
import org.apache.log4j.Logger;

public class BankOperations {
    final static Logger logger=Logger.getLogger(BankOperations.class);

    AccountDataBaseImpl accountDataBase=new AccountDataBaseImpl();
    CardDataBaseImpl cardDataBase=new CardDataBaseImpl();
    AtmDataBaseImpl atmDataBase=new AtmDataBaseImpl();
    HistoryDataBaseImpl historyDataBase=new HistoryDataBaseImpl();


    public void createAccount(int id, int inn, int depositUah){
        if(accountDataBase.getAccount(id)==null) {
            accountDataBase.addAccount(id, inn, depositUah);
            historyDataBase.addHistory("Create account " + id + ", inn " + inn + ", deposit" + depositUah);
            logger.info(historyDataBase.getLastHistory().getTransaction());
        }else {
            logger.info("Account already exists");
        }
    }

    public void  createCard(int id, int accountId, String pin, String type, String dateMMyy, boolean chip){
        if(cardDataBase.getCard(id)==null) {
            cardDataBase.addCard(id, accountId, pin, type, dateMMyy, chip);
            historyDataBase.addHistory("Create card " + id + ", for account " + accountId + ", type " + type + ", date " + dateMMyy + ", chip " + chip);
            logger.info(historyDataBase.getLastHistory().getTransaction());
        }else {
            logger.info("Card already exists");
        }
    }

    public void createAtm(int id, int depositUah){
        if(atmDataBase.getAtm(id)==null){
            atmDataBase.addAtm(id, depositUah);
            historyDataBase.addHistory("Create atm "+id+", with deposit "+depositUah+" uah");
            logger.info(historyDataBase.getLastHistory().getTransaction());
        }else {
            logger.info("Atm already exists");
        }
    }
}
