package quartettgame;

import framework.Card;
import framework.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

/**
 * Created by trixi on 2017.02.28..
 */
public class QuartettDeck extends Deck {

    public QuartettDeck(ArrayList<Card> cards) {
        super(cards);
    }

    @Override
    public void shuffleCards() {
        Collections.shuffle(getCards());
    }
}
