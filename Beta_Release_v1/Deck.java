
public class Deck {
    private Card[] deck;
    private final int SIZE = 52;

    public Deck() {
        deck = new Card[SIZE];
        for (int i = 0; i < SIZE; i++) {
            deck[i] = new Card(i % 13, i % 4);
        }
    }

    public int getSize() {
        return SIZE;
    }

    public Card getCardByIndex(int index)
    {
        return deck[index];
    }

    public void setCardByIndex(Card card, int index)
    {
        deck[index] = card;
    }

    @Override
    public String toString() {
        String deckStr = "\n";
        for (int i = 0; i < SIZE; i++) {
            deckStr += deck[i].toString() + '\n';
        }
        return "Deck{" + deckStr +"}";
    }
}
