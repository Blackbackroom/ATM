package atm.service;

import atm.model.Card;
import atm.persisting.impl.CardDataBaseImpl;

public class CardChecker {
CardDataBaseImpl cardDataBase = new CardDataBaseImpl();

    public boolean check(long id){
        return cardDataBase.getCard(id).isBlock();
    }

    public boolean checkPin(long id, String pin){
        if (cardDataBase.getCard(id).getPin().equalsIgnoreCase(pin)){
            return true;
        }else {
            return false;
        }
    }

    public void blockCard(long id, String reason){
        Card card=cardDataBase.getCard(id);
        card.setBlock(true);
        card.setReason(reason);
        cardDataBase.update(card);
    }

}
