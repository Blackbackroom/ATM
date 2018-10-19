package atm.service;

import atm.model.Card;
import atm.persisting.impl.CardDataBaseImpl;
import org.apache.log4j.Logger;

import java.util.Date;

public class CardOperation {
    final static Logger logger=Logger.getLogger(CardOperation.class);
    CardDataBaseImpl cardDataBase=new CardDataBaseImpl();
    Card card;


    public Card getCard(int id){
        return cardDataBase.getCard(id);
    }

    public void blockCard(int id, String reason){
        card=getCard(id);
        card.setBlock(true);
        card.setReason(reason);
        cardDataBase.update(card);
        logger.error("Card blocked");
    }

    public boolean checkCardDate(int id){
        Date today=new Date();
        card=getCard(id);
        return card.getEndCard().after(today);
    }

    public boolean checkCardBlock(int id){
        card=getCard(id);
        return !card.isBlock();
    }

    public boolean checkCardPin(int id, String pin){
        card=getCard(id);
        return card.getPin().equalsIgnoreCase(pin);
    }

    public void setCardDataBase(CardDataBaseImpl cardDataBase) {
        this.cardDataBase = cardDataBase;
    }

}
