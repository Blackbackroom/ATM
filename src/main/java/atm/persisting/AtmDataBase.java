package atm.persisting;

import atm.model.Atm;

public interface AtmDataBase {
    void addAtm(Atm atm);
    Atm getAtm(int id);
    void updateAtm(Atm atm);
    void deleteAtm(int id);
}
