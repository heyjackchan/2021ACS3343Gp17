
public class Card {

    public static final char[] CARD_VALUE = {'3', '4', '5', '6', '7', '8', '9', 'X', 'J', 'Q', 'K', 'A', '2'};
    public static final char[] CARD_SUIT = {'D', 'C', 'H', 'S'};

    private int index;
    private int value;
    private int suit;
    private boolean played;


    public Card() {
        index = -5;
        value = -1;
        suit = -1;
        played = false;
    }

    public Card(int value, int suit) {
        this.value = value;
        this.suit = suit;
        this.index = value * 4 + suit;
    }

    public int getIndex() {
        this.index = value * 4 + suit;
        return this.index;
    }

    public int getValue() {
        return value;
    }

    public int getSuit() {
        return suit;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public static char printValue(int value) {
        return Card.CARD_VALUE[value];
    }

    public static char printSuit(int suit) {
        return Card.CARD_SUIT[suit];
    }

    @Override
    public String toString() {
        return "Card{" +
                "index= " + getIndex() +
                ", value= " + getValue() +
                ", suit= " + getSuit() +
                ", CardSymbol= " + printSuit(getSuit()) + printValue(getValue()) + '}' ;
    }
}
