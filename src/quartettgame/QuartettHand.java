package quartettgame;

import framework.Card;
import framework.Hand;

import java.util.List;

public class QuartettHand extends Hand
{
    private List<QuartettCard> quartettCards;

    public QuartettHand(List<QuartettCard> cards)
    {
        this.quartettCards = cards;
    }

    public List<QuartettCard> getCards()
    {
        return quartettCards;
    }

    @Override
    public void addCard(Card card)
    {
        quartettCards.add((QuartettCard)card);
    }

    public QuartettCard getTopCard()
    {
        return quartettCards.get(0);
    }

    @Override
    public String toString()
    {
        // ez nem a QuartettCard-ba kell inkább?
    }
}
