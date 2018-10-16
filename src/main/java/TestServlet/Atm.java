package TestServlet;

public class Atm {
    private int id;
    private int uah;

    public Atm(int id, int uah) {
        this.id = id;
        this.uah = uah;
    }

    public int getId() {
        return id;
    }

    public int getUah() {
        return uah;
    }
}
