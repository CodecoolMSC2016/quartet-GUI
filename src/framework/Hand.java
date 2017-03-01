package framework;

import framework.Card;

import java.util.List;

/**
 * Created by trixi on 2017.02.28..
 */
public abstract class Hand
{
        private List<Card> cards;

        public abstract Card getCard();
        public abstract String toString();
        public abstract void addCard();
}
