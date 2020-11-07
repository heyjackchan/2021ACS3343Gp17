
public class Combination {

    private Card[] combination;
    private int cardNo;

    public Combination() {
        cardNo = 0;
        this.combination = new Card[5];
    }

    public Combination(Combination combination) {
     if(combination != null) {
            this.combination = new Card[5];
            for (int i = 0; i < combination.getCardNo(); i++) {
                this.combination[i] = new Card(combination.getCardByIndex(i));
                this.cardNo++;
            }
        } else {
            this.combination = null;
            this.cardNo = 0;
        }
    }

    public void addCardToCombination(Card card) {
        if (cardNo < 5) {
            combination[cardNo] = new Card(card);
            cardNo++;
        }
    }
    public int getCardNo() {
        return cardNo;
    }

    public Card getCardByIndex(int index)
    {
        return combination[index];
    }

    public void setCardByIndex(Card card, int index)
    {
        combination[index] = new Card(card);
    }

    public void sort()
    {
        boolean flag = true;
        Card temp;
        for (int i = 1; (i <= getCardNo()) && flag; i++)
        {
            flag = false;
            for (int j = 0; j < (getCardNo() - 1); j++)
            {
                if (combination[j + 1].getIndex() < combination[j].getIndex())
                {
                    temp = combination[j];
                    combination[j] = combination[j + 1];
                    combination[j + 1] = temp;
                    flag = true;
                }

            }
        }
        temp = null;
    }


    public void clearCombination() {
        for (int i = 0; i < cardNo; i++) {
            combination[i] = null;
        }
        cardNo = 0;
    }

    @Override
    public String toString() {
        String combinationStr = "";
        if(combination != null) {
            for (int i = 0; i < cardNo; i++) {
                combinationStr += combination[i].toString() + '\n';
            }
        }
        return "combination{\ncardNo=" + cardNo + ", \n" + combinationStr +"}";
    }
}
