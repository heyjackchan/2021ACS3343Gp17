
public class Deck extends CardPile{

    public Deck() {
        super(52);
        cards = new Card[capacity];
        for (int i = 0; i < capacity; i++) {
            cards[i] = new Card(i % 13, i % 4);
        }
    }

    @Override
    public String toString() {
        String deckStr = "\n";
        for (int i = 0; i < capacity; i++) {
            deckStr += cards[i].toString() + '\n';
        }
        return "Deck{" + deckStr +"}";
    }
}
