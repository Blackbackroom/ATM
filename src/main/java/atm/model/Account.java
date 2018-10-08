package atm.model;

public class Account {

    private long id;
    private long inn;
    private Money balance;

    public Account(long id, long inn, int amount) {
        this.id = id;
        this.inn = inn;
        balance.setUah(amount);
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
