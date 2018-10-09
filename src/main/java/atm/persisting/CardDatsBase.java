package atm.persisting;

import atm.model.Card;

public interface CardDatsBase {
    void addCard(Card card);
    Card getCard(long id);
    void update(Card card);
    void delete(long id);
}
