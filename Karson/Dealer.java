import java.util.Random;

public class Dealer {
    private Deck deck;
    private int cardNo;

    public Dealer() {
        cardNo = 0;
        deck = new Deck();
    }

    public Dealer(Deck deck) {
        this.deck = deck;
    }

    public void createDeck() {
        deck.createDeck();
        // System.out.println(deck.toString());
    }

    public void shuffle()
    {
        Random rand = new Random();
        for (int i = 0; i < 51; i++) {
            int r = i + (rand.nextInt(52 - i));
            Card temp = deck.getCardByIndex(i);
            deck.setCardByIndex(deck.getCardByIndex(r), i);
            deck.setCardByIndex(temp, r);

        }
        // System.out.println(deck.toString());
    }

    public void deal(Hand hand) {
        hand.add(deck.getCardByIndex(cardNo));
        cardNo++;
        if(cardNo == 51)
            cardNo = 0;
    }
}
