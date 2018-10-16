package atm.service;


import atm.model.Card;
import org.apache.log4j.Logger;
import java.util.Date;

public class CardOperations {

final static Logger logger=Logger.getLogger(CardOperations.class);

public boolean checkCard(Card card){
    if(card.isBlock()==false){
        Date today=new Date();
        if(today.before(card.getEndCard())==true){
           return true;
        }else return false;

    }else{
        return false;
    }
}


public boolean checkPin(Card card, String pin){
        if(card.getPin().equals(pin)==true){
            return true;
        }
        else return false;

}

}
