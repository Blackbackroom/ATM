package atm.model;

public class Account {

    private int id;
    private int inn;
    private int uah;


    public Account(int id, int inn, int uah) {
        this.id = id;
        this.inn = inn;
        this.uah=uah;
    }

    public void withdraw(int amountUah){
        uah=uah-amountUah;
    }

    public void deposit(int amountUah){
        uah=uah+amountUah;
    }

    public int getId() {
        return id;
    }

    public int getInn() {
        return inn;
    }

    public int getBalance() {
        return uah;
    }
}
