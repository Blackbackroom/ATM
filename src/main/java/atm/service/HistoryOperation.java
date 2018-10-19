package atm.service;

import atm.persisting.impl.HistoryDataBaseImpl;
import atm.persisting.impl.utils.MySQLConnector;

public class HistoryOperation {
    HistoryDataBaseImpl historyDataBase=new HistoryDataBaseImpl();

    public HistoryOperation(){
        historyDataBase.setMySQLConnector(new MySQLConnector());
    }

    public void addHistory(String transaction){
        historyDataBase.addHistory(transaction);
    }

    public String getLastHistory(){
        return historyDataBase.getLastHistory().getDate().toString()+historyDataBase.getLastHistory().getTime().toString()+historyDataBase.getLastHistory().getTransaction();
    }

}
