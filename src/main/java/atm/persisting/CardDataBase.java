package atm.persisting;

import atm.model.Card;
import atm.model.TypeCard;

public interface CardDataBase {
    void addCard(long id, long accountId, String pin, TypeCard typeCard, String dateMMyy, boolean chip);
    Card getCard(long id);
    void update(Card card);
    void delete(long id);
}
