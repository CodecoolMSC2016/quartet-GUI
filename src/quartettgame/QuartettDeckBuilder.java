package quartettgame;

import framework.Card;
import framework.Deck;
import framework.DeckBuilder;
import framework.Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class QuartettDeckBuilder implements DeckBuilder, Parser
{
    private List<List<String>> dataList;
    private String csvfile = "masterCrokCards.csv";
    private BufferedReader br;

    @Override
    public Deck buildDeck()
    {
        Deck quartettDeck = new QuartettDeck();
        parseFile();
        for (List row : dataList)
        {
            quartettDeck.addCard(createCard(row));
        }
        return quartettDeck;
    }
    private AttributeLevel findEnum(int value)
    {
        for (AttributeLevel a: AttributeLevel.values())
        {
            if(a.getValue() == value)
            {
                return a;
            }

        }
        return null;
    }
    private Card createCard(List row)
    {
        String name, description;
        AttributeLevel power, intelligence, reflex;

        name = (String) row.get(0); description = (String) row.get(1);
        power = findEnum(((int)row.get(2)));
        intelligence = findEnum(((int)row.get(3)));
        reflex = findEnum(((int)row.get(4)));
        Card newCard = new QuartettCard(name, description, power, intelligence, reflex);
        return newCard;

    }

    @Override
    public void parseFile()
    {
        List<List<String>> dataList = new ArrayList<>();
        String line = "", csvSplitby = ",";
        try
        {
            br = new BufferedReader(new FileReader(csvfile));
            while((line = br.readLine()) != null)
            {
                String[] tempArray = line.split(csvSplitby);
                dataList.add(Arrays.asList(tempArray));
            }
        }
        catch(Exception e)
        {
            System.out.println("Wrong data");
        }
    }
}
