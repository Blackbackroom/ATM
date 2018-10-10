package atm.service;

import atm.model.Atm;
import atm.persisting.impl.AtmDataBaseImpl;
import atm.persisting.impl.HistoryDataBaseImpl;

public class AtmOperations {
    AtmDataBaseImpl atmDataBase = new AtmDataBaseImpl();
    HistoryDataBaseImpl historyDataBase = new HistoryDataBaseImpl();

    Atm atm=atmDataBase.getAtm(1);

    CardChecker cardChecker = new CardChecker();
    AccountChecker accountChecker = new AccountChecker();

    private long cardId=-1;
    private boolean checkCard=false;
    private boolean checkPin=false;


    public void inputCard(long id){
        if(cardChecker.check(id)==true){
            cardId=id;
            checkCard=true;
        }
    }

    public void enterPin(String pin){
        if(checkCard==true){
            int count=1;
            if(cardChecker.checkPin(cardId, pin)==true){
                checkPin=true;
            }else{
                count++;
                if(count>3){
                    cardChecker.blockCard(cardId, "Wrong pin entered 3 times");
                    returnCard();
                }
            }


        }
    }

    public void showBalance(boolean sms, boolean display, boolean bill) {

    }

    public void transfer(long destination, int amount){

    }

    public void withdraw(int amount){

    }

    public void returnCard(){
        cardId=-1;
        checkPin=false;
        checkCard=false;
    }

    private boolean haveAmount(int amount){
        return atmDataBase.getAtm(atm.getId()).getCash().getUah()>amount;
    }


}
