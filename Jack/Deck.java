public class Deck{
    private Card[] deck;
    private Card card;
    private int cardNo;

    public Deck(){
        deck = new Card[52];
        for (int i = 0; i < 52; i++) { 
        	deck[i] = new Card();
        }
        	cardNo = 0;
    }

    public void CreateDeck() {
        for (int i = 0; i < 52; ++i) {
            deck[i] = card.setSuit(i % 4);
            deck[i] = card.setValue(i % 13);
            cardNo++;
        }
    }

    public void setCard(Card card,int n) {
        deck[n] = card;
    }
    
    public Card getCard(int n) {
        return deck[n];
    }
    
    public int getNoCard() {
        return cardNo;
    }
    
    public void clear() {
        Card card = new Card;
        for (int i = 0; i < 52; i++) {
            deck[i] = card;
        }
        cardNo = 0;
        //delete card;
    }
}
