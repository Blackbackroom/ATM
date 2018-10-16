package atm.persisting;

import atm.model.Account;

public interface AccountDataBase {
    void addAccount(Account account);
    Account getAccount(int id);
    void updateAccount(Account account);
    void deleteAccount(int id);

}
