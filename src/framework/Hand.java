package framework;

import framework.Card;

import java.util.List;

public abstract class Hand
{
        protected List<Card> cards;

        public Hand(List<Card>  cards)
        {
            this.cards = cards;
        }

        public List<Card> getCard() {
                return cards;
        }

        public abstract void addCard(Card card);

}
