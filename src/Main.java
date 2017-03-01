import framework.Game;
import quartettgame.QuartettDeckBuilder;
import quartettgame.QuartettGame;

/**
 * Created by trixi on 2017.02.28..
 */
public class Main
{
    public static void main(String[] args) {
        Game quartetGame = new QuartettGame(new QuartettDeckBuilder("masterCrokCards.csv"), 3);
        quartetGame.run();
    }
}
