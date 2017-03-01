package framework;

import framework.Card;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by trixi on 2017.02.28..
 */
public abstract class Deck
{
    private HashMap<String, Card> cards;

    public abstract void addCard();
    public abstract Set shuffleCards();
}
