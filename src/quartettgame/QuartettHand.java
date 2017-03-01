package quartettgame;

import framework.Card;
import framework.Hand;

import java.util.ArrayList;
import java.util.List;

public class QuartettHand extends Hand
{


    public Card getTopCard()
    {
        return cards.get(0);
    }

}
