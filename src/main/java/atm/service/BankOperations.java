package atm.service;

import atm.model.TypeCard;
import atm.persisting.impl.AccountDataBaseImpl;
import atm.persisting.impl.AtmDataBaseImpl;
import atm.persisting.impl.CardDataBaseImpl;
import org.apache.log4j.Logger;


public class BankOperations {
    AccountDataBaseImpl accountDataBase = new AccountDataBaseImpl();
    CardDataBaseImpl cardDataBase = new CardDataBaseImpl();
    AtmDataBaseImpl atmDataBase = new AtmDataBaseImpl();
    final static Logger logger=Logger.getLogger(BankOperations.class);

    public void createAccount(long id, long inn, int uah){
        if(accountDataBase.getAccount(id)!=null){
            logger.error("Account with id: "+id+ " already exists");
        }else {
            accountDataBase.addAccount(id, inn, uah);
        }
    }

    public void createCard(long id, long accountId, String pin, String typeCard, String dateMMyy, boolean chip){
        if(cardDataBase.getCard(id)!=null){
            logger.error("Card with id: "+id+ " already exists");
        }else{
            if(typeCard.equalsIgnoreCase("visa"))
            cardDataBase.addCard(id,accountId,pin,TypeCard.VISA,dateMMyy,chip);
            cardDataBase.addCard(id,accountId,pin,TypeCard.MASTERCARD,dateMMyy,chip);
        }
    }

    public void createAtm(int id, int deposit){
        if(atmDataBase.getAtm(id)!=null){
            logger.error("ATM with id: "+id+" already exists");
        }
    }

}
