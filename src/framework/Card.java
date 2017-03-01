package framework;

/**
 * Created by trixi on 2017.02.28..
 */
public abstract class Card implements Comparable
{
    private String name;

    public Card(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean equals(Card card);
    public abstract int hashCode();
    public abstract int compareTo(Card card);


}
