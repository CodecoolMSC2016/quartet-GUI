package quartettgame;

import framework.Card;
import framework.Player;
import framework.Hand;

import java.util.Arrays;
import java.util.Scanner;


/**
 * Created by trixi on 2017.02.28..
 */
public class QuartettPlayer extends Player
{
    private Card topCard;

    public QuartettPlayer(String name, Hand hand) {
        super(name,hand);
    }

    public int checkNumberOfCards()
    {
       return hand.getCard().size();
    }

    public void showCard()
    {
        topCard =((QuartettHand) hand).getTopCard();
    }

    public String pickAttribute() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Pick Attribute(P -> Power, I -> Intelligence R -> Reflex): ");
        String [] correctInput= {"P","I","R",} ;
        String userInput = "";
        while (true){

            userInput = scanner.nextLine();
            userInput = userInput.toUpperCase();
            if(Arrays.asList(correctInput).contains(userInput))break;
            System.out.println("Wrong input");
            System.out.println("Correct inputs: P -> Power, I -> Intelligence R -> Reflex");

        }
        return userInput;
    }
}
