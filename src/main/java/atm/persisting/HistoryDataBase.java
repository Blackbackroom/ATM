package atm.persisting;

import atm.model.History;

public interface HistoryDataBase {
    void addHistory(String reason);
    History getLastHistory();
}
