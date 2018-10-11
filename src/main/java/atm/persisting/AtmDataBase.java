package atm.persisting;

import atm.model.Atm;

public interface AtmDataBase {
    void addAtm(int id, int uah);
    Atm getAtm(int id);
    void updateAtm(int id, int uah);
    void deleteAtm(int id);
}
