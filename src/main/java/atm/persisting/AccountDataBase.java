package atm.persisting;

import atm.model.Account;

public interface AccountDataBase {
    void addAccount(int id, int inn, int uah);
    Account getAccount(int id);
    void updateAccount(int id, int uah);
    void deleteAccount(int id);

}
