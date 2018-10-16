package atm.service;

import atm.persisting.impl.HistoryDataBaseImpl;

public class HistoryOperations {
    HistoryDataBaseImpl historyDataBase = new HistoryDataBaseImpl();

    public void addHistory(String transaction){
        historyDataBase.addHistory(transaction);
    }

    public String getLastHistory(){
        return historyDataBase.getLastHistory().getTransaction();
    }

}
