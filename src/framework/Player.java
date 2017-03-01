package framework;

import framework.Hand;

/**
 * Created by trixi on 2017.02.28..
 */
public abstract class Player
{
    private String name;
    private Hand hand;

    public Player(String name) {
        this.name = name;
    }
}
