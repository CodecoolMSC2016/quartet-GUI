package quartettgame;

import framework.*;
import gui.MasterCrokGUI;

import java.util.*;

/**
 * Created by trixi on 2017.02.28..
 */
public class QuartettGame implements Game
{
    private Deck quartetDeck;
    private QuartettPlayer[] playerArray;
    private int numOfPlayers;
    private QuartettCard currentBestCard;
    private List<QuartettCard> cardsFromTie;
    private Map<QuartettCard, QuartettPlayer> cardsInPlay;
    private MasterCrokGUI gui;
    private QuartettPlayer lastWinner;
    private boolean stopExecution;

    public QuartettGame(QuartettDeckBuilder b, int numOfPlayers, MasterCrokGUI gui) {
        cardsFromTie = new ArrayList<>();
        quartetDeck = b.buildDeck();
        playerArray = new QuartettPlayer[numOfPlayers];
        this.numOfPlayers = numOfPlayers;
        this.gui = gui;
    }

    public QuartettCard getCurrentBestCard() {
        return currentBestCard;
    }

    private void createPlayers(){
        for (int i = 0; i < playerArray.length; i++){
            playerArray[i] = new QuartettPlayer("Player " + (i+1), new QuartettHand());
        }

    }

    private QuartettPlayer pickStaringPlayer(){
        Random random = new Random();
        return playerArray[random.nextInt(numOfPlayers)];
    }

    public void initGame(){
        createPlayers();
        quartetDeck.shuffleCards();
        lastWinner = pickStaringPlayer();
        dealCards();
        gui.buildMainWindow();
        gui.buildSecondaryWindow();
        run();
    }
    @Override
    public void run() {
        QuartettCard currentCard;
        do {
            try{
                currentCard = (QuartettCard)(lastWinner.getHand().getTopCard());
                if (!checkWinCondition()){
                    gui.goGUI(winnerOfTheGame());
                    return;
                }
                gui.goGUI(currentCard.getPicturePath(), lastWinner);
            }catch (NullPointerException e){
                return;
            }
            stopExecution = false;
            while (!stopExecution) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }while(checkWinCondition());
        gui.goGUI(winnerOfTheGame());
    }

    public void setStopExecution(boolean stopExecution) {
        this.stopExecution =  stopExecution;
    }

    public Map<QuartettCard, QuartettPlayer> getCardsInPlay() {
        return cardsInPlay;
    }

    public void setLastWinner(QuartettPlayer player){
        lastWinner = player;
    }

    private boolean checkWinCondition(){
        for (QuartettPlayer player: playerArray){
            if (player.checkNumberOfCards() == 0){
                return false;
            }
        }
        return true;
    }
    public void giveCardsToWinner(QuartettPlayer winner){
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
                break;
            case "I":
                if(firstCard.getIntelligenceLevel() == secondCard.getIntelligenceLevel()){
                    return true;
                }
                break;
            case "R":
                if(firstCard.getReflexLevel() == secondCard.getReflexLevel()){
                    return true;
                }
                break;
        }
        return false;
    }

    public QuartettPlayer decideWinner(String input){
        boolean tie = false;
        List<QuartettCard> cardList = new ArrayList();
        QuartettPlayer winningPlayer;
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
            currentBestCard = null;
            return lastWinner;
        }
        currentBestCard = cardList.get(cardList.size()-1);
        winningPlayer = cardsInPlay.get(currentBestCard);
        System.out.println("--------------------");
        System.out.println("Winner of this round: " + winningPlayer.getName());
        System.out.println("Winning card: ");
        System.out.println(currentBestCard);
        return winningPlayer;
    }

    public void gatherCards(){
        cardsInPlay = new HashMap<>();
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
        }
    }
}
