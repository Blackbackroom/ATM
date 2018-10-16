package atm.service;

import atm.model.Account;
import atm.model.Card;
import atm.persisting.impl.AccountDataBaseImpl;
import atm.persisting.impl.CardDataBaseImpl;

public class AtmOperations {
    CardOperations cardOperations=new CardOperations();
    CardDataBaseImpl cardDataBase=new CardDataBaseImpl();
    AccountDataBaseImpl accountDataBase=new AccountDataBaseImpl();

    Card card;
    Account account;
    private boolean checkCard=false;
    private boolean checkPin=false;

    public void setCard(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }



    private int count=1;
    public boolean isCheckCard() {
        return checkCard;
    }

    public boolean isCheckPin() {
        return checkPin;
    }

    public void putCard(int id){
        card=cardDataBase.getCard(id);
        checkCard=cardOperations.checkCard(card);
    }


    public void checkPin(Card card, String pin){
        checkPin=cardOperations.checkPin(card,pin);

    }

}
