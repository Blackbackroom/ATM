package atm.model;

import org.apache.log4j.Logger;

public class Atm {
    final static Logger logger=Logger.getLogger(Atm.class);
    private int id;
    private Money cash;

    public Atm(int id, int deposit) {
        this.id = id;
        cash=new Money();
        cash.setUah(deposit);
        if(deposit<0){
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
