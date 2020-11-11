
public class Hand extends CardPile{

    public Hand() {
        super(13);
        for (int i = 0; i < capacity; i++) {
            cards[i] = new Card();
        }

    }

    public void releaseCard(int index) {
        cards[index].setDiscarded(true);
        cardNo--;
    }

    @Override
    public String toString() {
        String handStr = "\n";
        for (int i = 0; i < 13; i++) {
            handStr += cards[i].toString() + '\n';
        }
        return handStr;
    }
}
