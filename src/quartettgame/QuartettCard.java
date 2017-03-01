package quartettgame;

import framework.Card;

/**
 * Created by trixi on 2017.02.28..
 */
public class QuartettCard extends Card
{
    //private String name;
    private String description;
    private Enum enums1;
    private Enum enums2;
    private Enum enums3;

    public QuartettCard(String name) {
        super(name);
    }

    public QuartettCard(String name, String description, Enum enums1, Enum enums2, Enum enums3) {
        super(name);
        this.description = description;
        this.enums1 = enums1;
        this.enums2 = enums2;
        this.enums3 = enums3;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Card card) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int compareTo(Card card) {
        return 0;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
