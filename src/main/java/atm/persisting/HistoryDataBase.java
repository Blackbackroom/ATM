package atm.persisting;

import atm.model.History;

import java.util.Date;

public interface HistoryDataBase {
    void addHistory(String transaction);
    History getHistory(int number);
    int getMaxNumber();
}
