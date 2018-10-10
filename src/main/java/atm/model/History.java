package atm.model;

import java.util.Date;

public class History {
    private Date date;
    private int number;
    private String operaton;

    public History(Date date, int number, String operaton) {
        this.date = date;
        this.number = number;
        this.operaton = operaton;
    }

    public Date getDate() {
        return date;
    }

    public int getNumber() {
        return number;
    }

    public String getOperaton() {
        return operaton;
    }

    public String toString(){
        return String.valueOf(number)+String.valueOf(date)+String.valueOf(operaton);
    }
}
