
public class Deck {
    private Card[] deck;
    private int cardNo;

    public Deck() {
        cardNo = 0;
        deck = new Card[52];
    }

    public void createDeck() {
        for (int i = 0; i < 52; i++) {
            deck[i] = new Card(cardNo % 13, cardNo % 4);
            cardNo++;
        }
    }

    public Card getCardByIndex(int index)
    {
        return deck[index];
    }

    public void setCardByIndex(Card card, int index)
    {
        deck[index] = card;
    }

    public void clearDeck() {
        for (int i = 0; i < 52; i++) {
            deck[i] = null;
        }
        cardNo = 0;
    }

    @Override
    public String toString() {
        String deckStr = "\n";
        for (int i = 0; i < 52; i++) {
            deckStr += deck[i].toString() + '\n';
        }
        return "Deck{" + deckStr +"}";
    }
}
