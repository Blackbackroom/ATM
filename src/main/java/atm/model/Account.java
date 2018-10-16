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

    public void setId(int id) {
        this.id = id;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }

    public void setUah(int uah) {
        this.uah = uah;
    }

    public int getId() {
        return id;
    }

    public int getInn() {
        return inn;
    }

    public int getUah() {
        return uah;
    }
}
