
public class Play {

    protected Combination currentCombination;
    protected Combination previousCombination;
    protected SortStrategy sortStrategy;

    public Play() {
        currentCombination = new Combination();
        previousCombination = new Combination();
        sortStrategy = new BubbleSort();
    }

    public Play(SortStrategy sortStrategy) {
        this.currentCombination = new Combination();
        this.previousCombination = new Combination();
        this.sortStrategy = sortStrategy;
    }

    public void sort(){
        sortStrategy.sort(this.currentCombination);
    }

    public Combination getCurrentCombination() {
        return currentCombination;
    }

    public Combination getPreviousCombination() {
        return previousCombination;
    }

    private boolean isEqual(Combination combo, int from, int to, CardValueType type) {
        boolean equal = true;
        if (type.equals(CardValueType.VALUE)) {
            for (int i = from; i <= to; i++) {
                if (combo.getCardByIndex(from).getValue() != combo.getCardByIndex(i).getValue()) {
                    equal = false;
                    break;
                }
            }
        }
        if (type.equals(CardValueType.SUIT)) {
            for (int i = from; i <= to; i++) {
                if (combo.getCardByIndex(from).getSuit() != combo.getCardByIndex(i).getSuit()) {
                    equal = false;
                    break;
                }
            }
        }
        return equal;
    }

    private ComboType checkType(Combination combo) {
        ComboType type = ComboType.NONE;
        switch (combo.getCardNo()) {
            case 1:
                type = ComboType.SINGLE; //a card
                break;
            case 2:
                if (isEqual(combo, 0, 1,  CardValueType.VALUE))
                    type = ComboType.PAIR; //pair
                break;
            case 3:
                if (isEqual(combo, 0, 2,  CardValueType.VALUE))
                    type = ComboType.THREE_OF_KIND; // three card
                break;
            case 5:
                boolean straight = true;
                boolean flush = isEqual(combo, 0, 4, CardValueType.SUIT);

                for (int i = 0; i <= 3; i++) {
                    if (combo.getCardByIndex(i + 1).getValue() - combo.getCardByIndex(i).getValue() != 1
                            && !(combo.getCardByIndex(0).getValue() == 0 && combo.getCardByIndex(1).getValue() == 9 && combo.getCardByIndex(2).getValue() == 10 && combo.getCardByIndex(3).getValue() == 11 && combo.getCardByIndex(4).getValue() == 12
                            || combo.getCardByIndex(0).getValue() == 0 && combo.getCardByIndex(1).getValue() == 1 && combo.getCardByIndex(2).getValue() == 10 && combo.getCardByIndex(3).getValue() == 11 && combo.getCardByIndex(4).getValue() == 12
                            || combo.getCardByIndex(0).getValue() == 0 && combo.getCardByIndex(1).getValue() == 1 && combo.getCardByIndex(2).getValue() == 2 && combo.getCardByIndex(3).getValue() == 11 && combo.getCardByIndex(4).getValue() == 12
                            || combo.getCardByIndex(0).getValue() == 0 && combo.getCardByIndex(1).getValue() == 1 && combo.getCardByIndex(2).getValue() == 2 && combo.getCardByIndex(3).getValue() == 3 && combo.getCardByIndex(4).getValue() == 12))
                    straight = false;
                }


                if (straight && flush)
                    type = ComboType.STRAIGHT_FLUSH; // straight flush
                else if (isEqual(combo, 0, 3,  CardValueType.VALUE))
                    type = ComboType.FOUR_PLUS_ONE;  //four plus one
                else if (isEqual(combo, 1, 4,  CardValueType.VALUE)) {
                    type = ComboType.FOUR_PLUS_ONE; //four plus one

                    Card temp = combo.getCardByIndex(0);
                    for (int i = 0; i < 4; i++) {
                        combo.setCardByIndex(combo.getCardByIndex(i + 1), i);
                    }
                    combo.setCardByIndex(temp, 4);
                    temp = null;
                }
                else if (isEqual(combo, 0, 2, CardValueType.VALUE) && isEqual(combo, 3, 4,  CardValueType.VALUE))
                    type = ComboType.FULL_HOUSE; // three plus one
                else if (isEqual(combo, 0, 1, CardValueType.VALUE) && isEqual(combo, 2, 4,  CardValueType.VALUE)) {
                    type = ComboType.FULL_HOUSE; // three plus one

                    Card temp = combo.getCardByIndex(0);
                    Card temp2 = combo.getCardByIndex(1);
                    for (int i = 0; i < 3; i++) {
                        combo.setCardByIndex(combo.getCardByIndex(i + 2), i);
                    }
                    combo.setCardByIndex(temp, 3);
                    combo.setCardByIndex(temp2, 4);
                    temp = null;
                    temp2 = null;
                }
                else if (flush)
                    type = ComboType.FLUSH; // flush
                else if (straight)
                    type = ComboType.STRAIGHT; // straight
                break;

        }
        return type;
    }

    private boolean compare(Combination previousCombination, Combination currentCombination, int numberOfCard) {
        if (currentCombination.getCardByIndex(numberOfCard).getValue() > previousCombination.getCardByIndex(numberOfCard).getValue()) {
            return true;
        } else if (currentCombination.getCardByIndex(numberOfCard).getValue() == previousCombination.getCardByIndex(numberOfCard).getValue()) {
            if (currentCombination.getCardByIndex(numberOfCard).getSuit() > previousCombination.getCardByIndex(numberOfCard).getSuit()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public boolean isValidPlay() {
        ComboType previousType = checkType(previousCombination), currentType = checkType(currentCombination);
        boolean valid = false;
        boolean start;

        if (previousCombination.getCardNo() == 0) {
            start = true;
        } else {
            start = false;
        }

        if(start){
            if (currentType == ComboType.NONE) {
                valid = false;
            } else {
                valid = true;
            }
        } else {
            if (previousCombination.getCardNo() == currentCombination.getCardNo()){
                if (currentType == ComboType.NONE)
                    return false;
                if (currentType.compareTo(ComboType.THREE_OF_KIND) == 1) {
                    if (currentType.compareTo(previousType) == 1)
                        valid = true;
                }
                if(currentType == previousType){
                    switch (currentType) {
                        case SINGLE:
                            valid = compare(previousCombination, currentCombination, 0);
                            break;
                        case PAIR:
                            valid = compare(previousCombination, currentCombination, 1);
                            break;
                        case THREE_OF_KIND:
                        case FULL_HOUSE:
                            valid = compare(previousCombination, currentCombination, 2);
                            break;
                        case FOUR_PLUS_ONE:
                            valid = compare(previousCombination, currentCombination, 3);
                            break;
                        case STRAIGHT_FLUSH:
                        case STRAIGHT:
                            valid = compare(previousCombination, currentCombination, 4);
                            break;
                        case FLUSH:
                            if (previousCombination.getCardByIndex(0).getSuit() > currentCombination.getCardByIndex(0).getSuit()) {
                                valid = false;
                            } else if (previousCombination.getCardByIndex(0).getSuit() < currentCombination.getCardByIndex(0).getSuit()) {
                                valid = true;
                            } else if (currentCombination.getCardByIndex(4).getValue() > previousCombination.getCardByIndex(4).getValue()) {
                                valid = true;
                            } else {
                                valid = false;
                            }
                            break;
                        default:
                            valid = false;
                    }
                }
            }else{
                valid = false;

            }
        }
        return valid;
    }

    public Combination releaseCurrentCombination() {
        return new Combination(this.currentCombination);
    }
    
    @Override
    public String toString() {
        String playStr = "\ncurrentCombination={ \n" + currentCombination + ", \n";
        playStr += "}\npreviousCombination={ \n" + previousCombination + ", \n";
        playStr += "}\n";
        return playStr;
    }
}
