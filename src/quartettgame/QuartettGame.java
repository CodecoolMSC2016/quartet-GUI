package quartettgame;

import framework.Deck;
import framework.DeckBuilder;
import framework.Game;
import framework.Player;

import java.util.ArrayList;

/**
 * Created by trixi on 2017.02.28..
 */
public class QuartettGame implements Game
{
    /* meghatározza a framework.Player -eket
    *  felépíti a játékteret
    */
    private Deck quartetDeck;
    private Player[] playerArray;

    public QuartettGame(QuartettDeckBuilder b, int numOfPlayers) {
        quartetDeck = b.buildDeck();
        playerArray = new Player[numOfPlayers];
    }

    @Override
    public void run() {
        QuartettPlayer lasWinner = null;
        for (int i = 0; i < playerArray.length; i++){
            playerArray[i] = new QuartettPlayer("Player " + (i+1), new QuartettHand());
        }
        /*do {
            quartetDeck.shuffleCards();
            dealCards();
            lasWinner = (QuartettPlayer) playerArray[1];
            lasWinner.pickAttribute();


        }while ();*/
    }

    public void dealCards() {

    }
}
