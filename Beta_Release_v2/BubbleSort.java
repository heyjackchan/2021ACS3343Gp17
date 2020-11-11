
public class BubbleSort implements SortStrategy {
    @Override
    public void sort(CardPile cards) {
        boolean flag = true;
        Card temp;
        for (int i = 1; (i <= cards.getCardNo()) && flag; i++)
        {
            System.out.print("cards.getCardNo() : \n" + cards.getCardNo());
            flag = false;
            for (int j = 0; j < (cards.getCardNo() - 1); j++)
            {
                if (cards.getCardByIndex(j + 1).getIndex() < cards.getCardByIndex(j).getIndex())
                {
                    temp = cards.getCardByIndex(j);
                    cards.setCardByIndex(cards.getCardByIndex(j + 1), j);
                    cards.setCardByIndex(temp, j + 1);
                    flag = true;
                }
            }
        }
    }
}
