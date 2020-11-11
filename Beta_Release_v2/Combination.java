
public class Combination extends CardPile {
    public Combination() {
        super(5);
    }

    public Combination(CardPile combination) {
        super(combination);
    }

    @Override
    public String toString() {
        String combinationStr = "";
        if(cards != null) {
            for (int i = 0; i < cardNo; i++) {
                combinationStr += cards[i].toString() + '\n';
            }
        }
        return "combination{\ncardNo=" + cardNo + ", \n" + combinationStr +"}";
    }


}
