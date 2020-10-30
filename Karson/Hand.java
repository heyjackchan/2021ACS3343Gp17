
public class Hand {

    private Card[] hand;
    private int cardNo;

    public Hand() {
        hand = new Card[13];
        for (int i = 0; i < 13; i++) {
            hand[i] = new Card();
        }
        cardNo = 0;
    }

    public void sort() {
        boolean flag = true;
        Card temp;
        for (int i = 1; (i <= 13) && flag; i++)
        {
            flag = false;
            for (int j = 0; j < 12; j++)
            {
                if (hand[j + 1].getIndex() < hand[j].getIndex())
                {
                    temp = hand[j];
                    hand[j] = hand[j + 1];
                    hand[j + 1] = temp;
                    flag = true;
                }

            }
        }
        temp = null;
    }

    public void add(Card card) {
        if (cardNo < 13) {
            hand[cardNo] = card;
            cardNo++;
        }
    }

    public Card getCardByIndex(int index) {
        return hand[index];
    }


    public int getCardNo() {
        return cardNo;
    }


    public void releaseCard(int index) {
        hand[index].setPlayed(true);
        cardNo--;
    }

    public void clearHand()
    {
        for (int i = 0; i < 13; i++) {
            hand[i] = null;
        }
        cardNo = 0;
    }

    @Override
    public String toString() {
        String handStr = "\n";
        for (int i = 0; i < 13; i++) {
            handStr += hand[i].toString() + '\n';
        }
        return handStr +"}";
    }
}
