package atm.model;

import org.apache.log4j.Logger;

public class Atm {
    final static Logger logger=Logger.getLogger(Atm.class);
    private int id;
    private Money cash;

    public Atm(int id, int cash) throws Exception {
        this.id = id;
        if(cash<0){
            throw new Exception("Cash can't be <0");
        }
        try{
        this.cash.setUah(cash);}catch (Exception e){
            logger.error("Cash can't be <0");
        }

    }

    public int getId() {
        return id;
    }

    public Money getCash() {
        return cash;
    }

    public void withdraw(int amount){
        cash.setUah(cash.getUah()-amount);
    }

    public void deposit(int amount){
        cash.setUah(cash.getUah()+amount);
    }

}
