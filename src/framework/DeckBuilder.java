package framework;

import framework.Deck;

/**
 * Created by trixi on 2017.02.28..
 */
public interface DeckBuilder
{
    Deck buildDeck();
    void parseFile();
}
