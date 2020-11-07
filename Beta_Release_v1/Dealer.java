import java.util.Random;

public class Dealer {
    private Deck deck;
    private int cardNo = 0;

    public Dealer() {
        deck = new Deck();
        // System.out.println(deck.toString());
    }

    public void shuffle()
    {
        Random rand = new Random();
        for (int i = 0; i < deck.getSize() - 1; i++) {
            int r = i + (rand.nextInt(deck.getSize() - i));
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
