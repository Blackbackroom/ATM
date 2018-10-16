package atm.service;

import atm.model.Card;
import atm.persisting.impl.CardDataBaseImpl;

import java.util.Date;


public class CardService {

Card card;
CardDataBaseImpl cardDataBase=new CardDataBaseImpl();

public boolean checkCard(int id){
    if((checkDate(id)==true)&&(checkBlock()==true)){
        return true;
    }else {
        return false;
    }
}

private boolean checkDate(int id){
    card=cardDataBase.getCard(id);
    Date today = new Date();
    if(card.getEndCard().after(today)){
        return true;
    }else {
        blockCard("Error date");
        return false;
    }
}

private boolean checkBlock(){
    if(card.isBlock()==false){
        return true;
    }else{
        return false;
    }
}

public void blockCard(String reason){
    card.setBlock(true);
    cardDataBase.update(card.getId(),true, reason);
}

public boolean checkPin(String pin){
    if(card.getPin().equalsIgnoreCase(pin)){
        return true;
    }else{
        return false;
    }
}

}
