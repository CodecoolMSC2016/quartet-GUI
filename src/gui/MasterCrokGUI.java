package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;


/**
 * Created by trixi on 2017.03.02..
 */
public class MasterCrokGUI
{
    private JFrame frame;
    private JLabel label;
    private JPanel panel;
    private JButton buttonPower;
    private JButton buttonIntelligence;
    private JButton buttonReflex;

    public void goGUI()
    {
        JFrame frame = new JFrame("Your Crok");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("src/pictures/masterCrok.jpg"));
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        JButton buttonPower = new JButton("Power");
        buttonPower.addActionListener(new PowerButtonListener());

        JButton buttonIntelligence = new JButton("Intelligence");
        buttonIntelligence.addActionListener(new IntelligenceButtonListener());

        JButton buttonReflex = new JButton("Reflex");
        buttonReflex.addActionListener(new ReflexButtonListener());

        ImageIcon imageIcon = new ImageIcon(image);
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        label = new JLabel (imageIcon);
        panel.add(label);
        panel.add(buttonPower);
        panel.add(buttonIntelligence);
        panel.add(buttonReflex);

        frame.add(panel);
        frame.setSize(400, 650);
        frame.setVisible(true);
    }
    class PowerButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            buttonPower.setText("Power benyomva");
        }
    }

    class IntelligenceButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            buttonIntelligence.setText("Intelligence benyomva");
        }
    }

    class ReflexButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            buttonReflex.setText("Reflex benyomva");
        }
    }
}
