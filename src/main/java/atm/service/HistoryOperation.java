package atm.service;

import atm.persisting.impl.HistoryDataBaseImpl;

public class HistoryOperation {
    HistoryDataBaseImpl historyDataBase=new HistoryDataBaseImpl();

    public void addHistory(String transaction){
        historyDataBase.addHistory(transaction);
    }

    public String getLastHistory(){
        return historyDataBase.getLastHistory().getDate().toString()+historyDataBase.getLastHistory().getTime().toString()+historyDataBase.getLastHistory().getTransaction();
    }

}
