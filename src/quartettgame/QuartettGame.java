package quartettgame;

import framework.*;

import java.util.*;

/**
 * Created by trixi on 2017.02.28..
 */
public class QuartettGame implements Game
{
    /* meghatározza a framework.Player -eket
    *  felépíti a játékteret
    */
    private Deck quartetDeck;
    private QuartettPlayer[] playerArray;
    private int numOfPlayers;
    private List<QuartettCard> cardsFromTie;
    private Map<QuartettCard, QuartettPlayer> cardsInPlay;

    public QuartettGame(QuartettDeckBuilder b, int numOfPlayers) {
        cardsFromTie = new ArrayList<>();
        quartetDeck = b.buildDeck();
        cardsInPlay = new HashMap<>();
        playerArray = new QuartettPlayer[numOfPlayers];
        this.numOfPlayers = numOfPlayers;
    }

    private void createPlayers(){
        QuartettPlayer lasWinner = null;
        for (int i = 0; i < playerArray.length; i++){
            playerArray[i] = new QuartettPlayer("Player " + (i+1), new QuartettHand());
        }

    }

    private QuartettPlayer pickStaringPlayer(){
        Random random = new Random();
        return playerArray[random.nextInt(numOfPlayers)];
    }
    @Override
    public void run() {
        int rounds = 1;
        createPlayers();
        quartetDeck.shuffleCards();
        dealCards();
        QuartettPlayer lastWinner = pickStaringPlayer();
        do {
            System.out.println("R O U N D : " + rounds);
            System.out.println("Current player: " + lastWinner.getName());
            System.out.println((lastWinner.showCard()));
            gatherCards();
            lastWinner = decideWinner(lastWinner.pickAttribute(), lastWinner);
            giveCardsToWinner(lastWinner);
            printCards();
            rounds++;

        }while (checkWinCondition());
        System.out.println(winnerOfTheGame().getName());

    }

    private void printCards(){
        for (QuartettCard card: cardsInPlay.keySet()){
            System.out.println("--------------------");
            System.out.println("--------------------");
            System.out.println(cardsInPlay.get(card).getName());
            System.out.println(card);
        }
        cardsInPlay.clear();

    }
    private boolean checkWinCondition(){
        for (QuartettPlayer player: playerArray){
            if (player.checkNumberOfCards() == 0){
                return false;
            }
        }
        return true;
    }
    private void giveCardsToWinner(QuartettPlayer winner){
        if (cardsFromTie.size() == 0){
            for (Card card: cardsInPlay.keySet()) {
                winner.getHand().addCard(card);
            }
        }else {
            for (Card card: cardsFromTie){
                winner.getHand().addCard(card);
            }
        }
    }

    public QuartettPlayer winnerOfTheGame(){
        QuartettPlayer winner = playerArray[0];
        for (QuartettPlayer p: playerArray){
            if (p.checkNumberOfCards() > winner.checkNumberOfCards()){
                winner = p;
            }
        }
        return winner;
    }

    private boolean checkTie(List<QuartettCard> cardList, String attribute){
        QuartettCard firstCard = cardList.get(cardList.size()-1);
        QuartettCard secondCard = cardList.get(cardList.size()-2);
        switch (attribute){
            case "P":
                if(firstCard.getPowerLevel() == secondCard.getPowerLevel()){
                    return true;
                }
            case "I":
                if(firstCard.getIntelligenceLevel() == secondCard.getIntelligenceLevel()){
                    return true;
                }
            case "R":
                if(firstCard.getReflexLevel() == secondCard.getReflexLevel()){
                    return true;
                }
        }
        return false;
    }

    private QuartettPlayer decideWinner(String input, QuartettPlayer lastWinner){
        boolean tie = false;
        List<QuartettCard> cardList = new ArrayList();
        QuartettPlayer winningPlayer;
        QuartettCard winningCard;
        cardList.addAll(cardsInPlay.keySet());
        switch (input){
            case "P":
                cardList.sort(new QuartettCard.PowerComparator());
                tie = checkTie(cardList, "P");
                break;
            case "I":
                cardList.sort(new QuartettCard.IntelligenceComparator());
                tie = checkTie(cardList, "I");
                break;
            case "R":
                cardList.sort(new QuartettCard.ReflexComparator());
                tie = checkTie(cardList, "R");
                break;
        }

        if (tie){
            System.out.println("There was a tie, collecting cards now for the next round.");
            cardsFromTie = cardList;
            return lastWinner;
        }
        winningCard = cardList.get(cardList.size()-1);
        winningPlayer = cardsInPlay.get(winningCard);
        System.out.println("Winner of this round: " + winningPlayer.getName());
        return winningPlayer;
    }

    private void gatherCards(){
        for (QuartettPlayer player: playerArray){
            cardsInPlay.put((QuartettCard)player.showCard(), player);
            player.getHand().removeTopCard();
        }
    }

    public void dealCards() {
        int index = 0;
        List<Card> currentDeck = quartetDeck.getCards();
        while(index < currentDeck.size()-4){
            for (QuartettPlayer p: playerArray){
                 p.getHand().addCard(currentDeck.get(index));
                index++;
            }
        };
    }
}
