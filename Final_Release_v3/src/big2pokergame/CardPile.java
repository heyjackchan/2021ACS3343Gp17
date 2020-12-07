package big2pokergame;

public abstract class CardPile {
    protected Card[] cards;
    protected int cardNo;
    protected int capacity;

    public CardPile(int capacity) {
        this.capacity = capacity;
        this.cardNo = 0;
        this.cards = new Card[capacity];
    }

    public CardPile(CardPile cards) {
        if (cards != null) {
            this.capacity = cards.getCardNo();
            this.cards = new Card[this.capacity];
            for (int i = 0; i < this.capacity; i++) {
                this.cards[i] = new Card(cards.getCardByIndex(i));
                this.cardNo++;
            }
        } else {
            this.cards = null;
            this.cardNo = 0;
        }
    }

    public void addCard(Card card) {
        if (cardNo < capacity) {
            cards[cardNo] = new Card(card);
            cardNo++;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public Card getCardByIndex(int index) {
        return cards[index];
    }

    public void setCardByIndex(Card card, int index) {
        cards[index] = new Card(card);
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            cards[i] = null;
        }
        cardNo = 0;
    }

    @Override
    public String toString() {
        String cardPileStr = "";
        if (cards != null) {
            for (int i = 0; i < capacity; i++) {
                cardPileStr += cards[i].toString() + '\n';
            }
        }
        return "combination{\ncardNo=" + cardNo + ", \n" + cardPileStr + "}";
    }

}
