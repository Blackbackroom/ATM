package atm.persisting;

import atm.model.Card;

public interface CardDataBase {
    void addCard(Card card);
    Card getCard(int id);
    void update(Card card);
    void delete(int id);
}
