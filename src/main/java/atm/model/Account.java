package atm.model;

public class Account {

    private int id;
    private int inn;
    private Money balance;


    public Account(int id, int inn, int uah) {
        this.id = id;
        this.inn = inn;
        balance=new Money();
        balance.setUah(uah);
    }

    public void withdraw(int amountUah){
        balance.setUah(balance.getUah()-amountUah);
    }

    public void deposit(int amountUah){
        balance.setUah(balance.getUah()+amountUah);
    }

    public long getId() {
        return id;
    }

    public long getInn() {
        return inn;
    }

    public Money getBalance() {
        return balance;
    }
}
