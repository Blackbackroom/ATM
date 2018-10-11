package atm.model;

import java.sql.Date;
import java.sql.Time;

public class History {
    Date date;
    Time time;
    String transaction;

    public History(String currentTimeFromDB, String transaction) {
        long currentTime=Long.valueOf(currentTimeFromDB);
        Date date1=new Date(currentTime);
        this.date=date1;
        Time time1=new Time(currentTime);
        this.time=time1;
        this.transaction = transaction;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public String getTransaction() {
        return transaction;
    }

    public String getHistory(){
        return date+" "+time+" "+transaction;
    }
}
