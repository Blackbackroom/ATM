package atm.model;

import org.apache.log4j.Logger;

public class Atm {
    final static Logger logger=Logger.getLogger(Atm.class);
    private int id;
    private int uah;

    public Atm(int id, int uah) {
        this.id = id;
        this.uah=uah;
        if(uah<0){
            logger.error("Cash can't be <0");
        }
    }

    public int getId() {
        return id;
    }

    public int getUah() {
        return uah;
    }

    public void withdraw(int amount){
        uah=uah-amount;
    }

    public void deposit(int amount){
        uah=uah+amount;
    }

}
