package framework;

import framework.Hand;

/**
 * Created by trixi on 2017.02.28..
 */
public abstract class Player
{
    protected String name;
    protected Hand hand;

    public Player(String name, Hand hand) {

        this.name = name;
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public abstract void showCard();
}
