public class Card {

    public static final char[] CARD_VALUE = {'3', '4', '5', '6', '7', '8', '9', 'X', 'J', 'Q', 'K', 'A', '2'};
    public static final char[] CARD_SUIT = {'D', 'C', 'H', 'S'};

    private int index;
    private int value;
    private int suit;
    private boolean discarded;


    public Card() {
        index = -5;
        value = -1;
        suit = -1;
        discarded = false;
    }

    public Card(int value, int suit) {
        this.value = value;
        this.suit = suit;
        this.index = value * 4 + suit;
        this.discarded = false;
    }

    public Card(Card card) {
        this.value = card.getValue();
        this.suit = card.getSuit();
        this.index = card.getIndex();
        this.discarded = card.isDiscarded();
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

    public boolean isDiscarded() {
        return discarded;
    }

    public void setDiscarded(boolean discarded) {
        this.discarded = discarded;
    }

    public String printCardSymbol() {
        return  Character.toString(CARD_SUIT[suit]) + CARD_VALUE[value];
    }

    @Override
    public String toString() {
        return "Card{" +
                "index= " + getIndex() +
                ", value= " + getValue() +
                ", suit= " + getSuit() +
                ", discarded= " + isDiscarded() +
                ", CardSymbol= " + printCardSymbol() + '}' ;
    }
}
