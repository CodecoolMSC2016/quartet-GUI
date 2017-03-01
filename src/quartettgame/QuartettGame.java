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
    private List<Card> cardsFromTie;
    private Map<Card, QuartettPlayer> cardsInPlay;

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
        createPlayers();
        quartetDeck.shuffleCards();
        dealCards();
        QuartettPlayer lastWinner = pickStaringPlayer();
        while (checkWinCondition()){
            System.out.println("Current player: " + lastWinner.getName());
            System.out.println((lastWinner.showCard()));
            gatherCards();
            lastWinner = decideWinner(lastWinner.pickAttribute());
            System.out.println("Winner of this round: " + lastWinner.getName());
            giveCardsToWinner(lastWinner);

        }

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
        cardsInPlay.clear();

    }

    private void checkTie(List<QuartettCard> cardList, String attribute){
        switch (attribute){
            case "P":
                if(cardList.get(0).getPowerLevel() == cardList.get(1).getPowerLevel()){
                    cardsFromTie = new ArrayList<>(cardList);
                }
            case "I":
                if(cardList.get(0).getIntelligenceLevel() == cardList.get(1).getIntelligenceLevel()){
                    cardsFromTie = new ArrayList<>(cardList);
                }
            case "R":
                if(cardList.get(0).getReflexLevel() == cardList.get(1).getReflexLevel()){
                    cardsFromTie = new ArrayList<>(cardList);
                }
        }
    }

    private QuartettPlayer decideWinner(String input){
        List cardList = new ArrayList();
        cardList.addAll(cardsInPlay.keySet());
        switch (input){
            case "P":
                cardList.sort(new QuartettCard.PowerComparator());
                checkTie(cardList, "P");
                break;
            case "I":
                cardList.sort(new QuartettCard.IntelligenceComparator());
                checkTie(cardList, "I");
                break;
            case "R":
                cardList.sort(new QuartettCard.ReflexComparator());
                checkTie(cardList, "R");
                break;
        }
        return cardsInPlay.get(cardList.get(0));
    }

    private void gatherCards(){
        for (QuartettPlayer player: playerArray){
            cardsInPlay.put(player.showCard(), player);
            ((QuartettHand) player.getHand()).removeTopCard();
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
