package gui;

import quartettgame.QuartettCard;
import quartettgame.QuartettGame;
import quartettgame.QuartettPlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.Set;


/**
 * Created by trixi on 2017.03.02..
 */
public class MasterCrokGUI
{
    private JFrame mainFrame;
    private JFrame secondFrame;
    private QuartettGame quartettGame;

    public MasterCrokGUI() {
        this.mainFrame = new JFrame();
    }

    public void addGame(QuartettGame game){
        quartettGame = game;
    }

    public void buildMainWindow(){
        mainFrame = new JFrame("Master Crok Game");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 650);
        mainFrame.setVisible(true);
    }

    public void buildSecondaryWindow(){
        secondFrame = new JFrame("Cards from previous round.");
        secondFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        secondFrame.setSize(1300, 650);
        secondFrame.setVisible(true);
    }

    public void showAllCards(QuartettPlayer winner){
        Set<QuartettCard> cardSet = quartettGame.getCardsInPlay().keySet();
        ImageIcon[] imageIcons = new ImageIcon[cardSet.size()];
        String picturePath;
        int index = 0;
        try {
            for (QuartettCard card : cardSet) {
                picturePath = card.getPicturePath();
                BufferedImage bufferedImage = ImageIO.read(new File(picturePath));
                imageIcons[index] = new ImageIcon(bufferedImage);
                index++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        JLabel textLabel = new JLabel();
        QuartettCard winningCard = quartettGame.getCurrentBestCard();
        if (winningCard == null){
            textLabel.setText("There was a TIE. Collecting card for the NEXT ROUND.");

        }else{
            textLabel.setText("WINNER OF THE ROUND:  " + winner.getName() + " : " + winningCard.getName());
        }

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        for (ImageIcon image: imageIcons){
            panel.add(new JLabel(image));
        }
        panel.add(textLabel);
        secondFrame.add(panel);
        secondFrame.setVisible(true);
    }

    public void goGUI(String picturePath, QuartettPlayer currentPlayer)
    {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(picturePath));
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(1);

        }
        JButton buttonPower = new JButton("Power");
        JButton buttonIntelligence = new JButton("Intelligence");
        JButton buttonReflex = new JButton("Reflex");


        buttonPower.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                quartettGame.gatherCards();
                QuartettPlayer winner = quartettGame.decideWinner("P");
                quartettGame.setLastWinner(winner);
                quartettGame.giveCardsToWinner(winner);
                showAllCards(winner);
                quartettGame.setStopExecution(true);
                quartettGame.getCardsInPlay().clear();
            }
        });
        buttonIntelligence.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                quartettGame.gatherCards();
                QuartettPlayer winner = quartettGame.decideWinner("I");
                quartettGame.setLastWinner(winner);
                quartettGame.giveCardsToWinner(winner);
                showAllCards(winner);
                quartettGame.setStopExecution(true);
                quartettGame.getCardsInPlay().clear();
            }
        });
        buttonReflex.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                quartettGame.gatherCards();
                QuartettPlayer winner = quartettGame.decideWinner("R");
                quartettGame.setLastWinner(winner);
                quartettGame.giveCardsToWinner(winner);
                showAllCards(winner);
                quartettGame.setStopExecution(true);
                quartettGame.getCardsInPlay().clear();
            }
        });

        ImageIcon imageIcon = new ImageIcon(image);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel (imageIcon);
        JLabel textLabel = new JLabel();
        textLabel.setText(currentPlayer.getName());
        textLabel.setSize(20,20);
        panel.add(label);
        panel.add(buttonPower);
        panel.add(buttonIntelligence);
        panel.add(buttonReflex);
        panel.add(textLabel);

        mainFrame.add(panel);
        mainFrame.setVisible(true);

    }

    public void goGUI(QuartettPlayer lastWinner){
        mainFrame.setVisible(false);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel textLabel = new JLabel();
        Dimension dimension = new Dimension();
        dimension.setSize(800,600);
        textLabel.setText("GAME OVER: WINNER is " + lastWinner.getName());
        textLabel.setPreferredSize(dimension);
        textLabel.setFont(new Font("Calibri", Font.BOLD, 36));
        panel.add(textLabel);
        secondFrame.add(panel);
        secondFrame.setVisible(true);
    }
}
