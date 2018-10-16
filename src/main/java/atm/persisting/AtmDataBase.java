package atm.persisting;

import atm.model.Atm;

public interface AtmDataBase {
    void addAtm(Atm atm);
    Atm getAtm(int id);
    void updateAtm(int id, int uah);
    void deleteAtm(int id);
}
