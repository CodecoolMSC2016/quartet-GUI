package framework;

import framework.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand
{
        protected List<Card> cards;

        public Hand()
        {
            cards = new ArrayList<Card>();
        }

        public List<Card> getCard() {
                return cards;
        }



        public void addCard(Card card)
        {
                cards.add(card);
        }

        public void addCard(ArrayList<Card> card) {

                cards.addAll(card);

        }


}
