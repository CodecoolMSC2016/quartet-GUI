package framework;

import framework.Card;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by trixi on 2017.02.28..
 */
public abstract class Deck
{
    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public  void addCard(Card card){
        cards.add(card);
    }

    public abstract void shuffleCards();
}
