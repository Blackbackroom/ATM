package atm.service;

import org.apache.log4j.Logger;

public class AtmService {
    final static Logger logger=Logger.getLogger(AtmService.class);

    // Variables to help
    private int cardId=-1;
    private int accountId=-1;
    private boolean checkCard=false;
    private boolean checkPin=false;
    CardService cardService = new CardService();


    public void putCard(int id){
        if((checkCard=cardService.checkCard(id))==true){
            this.cardId=id;
        }else{
            logger.error("Card no check");
        }
    }

    public void enterPin(String pin){
        if((checkCard==true)&&((checkPin=cardService.checkPin(pin))==true)){
            this.checkPin=true;
        }else{
            logger.error("Wrong pin");
        }
    }

    //Getters and setters
    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public boolean isCheckCard() {
        return checkCard;
    }

    public void setCheckCard(boolean checkCard) {
        this.checkCard = checkCard;
    }

    public boolean isCheckPin() {
        return checkPin;
    }

    public void setCheckPin(boolean checkPin) {
        this.checkPin = checkPin;
    }

}
