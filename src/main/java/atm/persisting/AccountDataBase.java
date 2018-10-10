package atm.persisting;

import atm.model.Account;

public interface AccountDataBase {
    void addAccount(long id, long inn, int uah);
    Account getAccount(long id);
    void updateAccount(Account account);
    void deleteAccount(long id);

}
