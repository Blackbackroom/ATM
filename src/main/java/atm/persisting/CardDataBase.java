package atm.persisting;

import atm.model.Card;

public interface CardDataBase {
    void addCard(int id, int accountId, String pin, String typeCard, String dateMMyy, boolean chip);
    Card getCard(int id);
    void update(int id, boolean block, String reason);
    void delete(int id);
}
