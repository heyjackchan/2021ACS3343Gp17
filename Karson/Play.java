import static com.company.Play.ComboType.*;

public class Play {


    private Card[] play;
    private Card[] pPlay;
    private int cardNo;
    private int pCardNo;

    public enum CardType {
        VALUE,
        SUIT
    }

    public enum ComboType {
        NONE,
        SINGLE,
        PAIR,
        THREE_OF_KIND,
        STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_PLUS_ONE,
        STRAIGHT_FLUSH
    }

    public Play(){
        cardNo = 0;
        pCardNo = 0;
        play = new Card[5];
        pPlay = new Card[5];
    }

    public boolean isEqual(Card[] combo, int from, int to, CardType type) {
        boolean equal = true;
        if (type == CardType.VALUE) {
            for (int i = from; i <= to; i++) {
                if (combo[from].getValue() != combo[i].getValue()) {
                    equal = false;
                    break;
                }
            }
        }
        if (type == CardType.SUIT) {
            for (int i = from; i <= to; i++) {
                if (combo[from].getSuit() != combo[i].getSuit()) {
                    equal = false;
                    break;
                }
            }
        }
        return equal;
    }

    public ComboType checkType(Card[] combo) {
        ComboType type = NONE;
        switch (getCardNo()) {
            case 1:
                type = SINGLE; //a card
                break;
            case 2:
                if (isEqual(combo, 0, 1,  CardType.VALUE))
                    type = PAIR; //pair
                break;
            case 3:
                if (isEqual(combo, 0, 2,  CardType.VALUE))
                    type = THREE_OF_KIND; // three card
                break;
            case 5:
                boolean straight = true;
                boolean flush = isEqual(combo, 0, 4, CardType.SUIT);

                for (int i = 0; i <= 3; i++) {
                    if (combo[i + 1].getValue() - combo[i].getValue() != 1
                            && !(combo[0].getValue() == 0 && combo[1].getValue() == 9 && combo[2].getValue() == 10 && combo[3].getValue() == 11 && combo[4].getValue() == 12
                            || combo[0].getValue() == 0 && combo[1].getValue() == 1 && combo[2].getValue() == 10 && combo[3].getValue() == 11 && combo[4].getValue() == 12
                            || combo[0].getValue() == 0 && combo[1].getValue() == 1 && combo[2].getValue() == 2 && combo[3].getValue() == 11 && combo[4].getValue() == 12
                            || combo[0].getValue() == 0 && combo[1].getValue() == 1 && combo[2].getValue() == 2 && combo[3].getValue() == 3 && combo[4].getValue() == 12))
                    straight = false;
                }


                if (straight && flush)
                    type = STRAIGHT_FLUSH; // straight flush
                else if (isEqual(combo, 0, 3,  CardType.VALUE))
                    type = FOUR_PLUS_ONE;  //four plus one
                else if (isEqual(combo, 1, 4,  CardType.VALUE)) {
                    type = FOUR_PLUS_ONE; //four plus one

                    Card temp = combo[0];
                    for (int i = 0; i < 4; i++)
                        combo[i] = combo[i + 1];
                    combo[4] = temp;
                    temp = null;
                }
                else if (isEqual(combo, 0, 2, CardType.VALUE) && isEqual(combo, 3, 4,  CardType.VALUE))
                    type = FULL_HOUSE; // three plus one
                else if (isEqual(combo, 0, 1, CardType.VALUE) && isEqual(combo, 2, 4,  CardType.VALUE)) {
                    type = FULL_HOUSE; // three plus one

                    Card temp = combo[0];
                    Card temp2 = combo[1];
                    for (int i = 0; i < 3; i++)
                        combo[i] = combo[i + 2];
                    combo[3] = temp;
                    combo[4] = temp2;
                    temp = null;
                    temp2 = null;
                }
                else if (flush)
                    type = FLUSH; // flush
                else if (straight)
                    type = STRAIGHT; // straight
                break;

        }
        return type;
    }

    public boolean compare(Card[] previousPlay, Card[] currentPlay, int numberOfCard) {
        if (currentPlay[numberOfCard].getValue() > previousPlay[numberOfCard].getValue()) {
            return true;
        } else if (currentPlay[numberOfCard].getValue() == previousPlay[numberOfCard].getValue()) {
            if (currentPlay[numberOfCard].getSuit() > previousPlay[numberOfCard].getSuit()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public boolean isValidPlay() {

        ComboType previousType = checkType(pPlay), currentType = checkType(play);
        boolean valid = false;
        boolean start;

        if (getpCardNo() == 0) {
            start = true;
        } else {
            start = false;
        }

        if(start){
            if (currentType == NONE) {
                valid = false;
            } else {
                valid = true;
            }
        } else {
            if (getpCardNo() == getCardNo()){
                if (currentType == NONE)
                    return false;
                if (currentType.compareTo(THREE_OF_KIND) == 1) {
                    if (currentType.compareTo(previousType) == 1)
                        valid = true;
                }
                if(currentType == previousType){
                    switch (currentType) {
                        case SINGLE:
                            valid = compare(pPlay, play, 0);
                            break;
                        case PAIR:
                            valid = compare(pPlay, play, 1);
                            break;
                        case THREE_OF_KIND:
                        case FULL_HOUSE:
                            valid = compare(pPlay, play, 2);
                            break;
                        case FOUR_PLUS_ONE:
                            valid = compare(pPlay, play, 3);
                            break;
                        case STRAIGHT_FLUSH:
                        case STRAIGHT:
                            valid = compare(pPlay, play, 4);
                            break;
                        case FLUSH:
                            if (pPlay[0].getSuit() > play[0].getSuit()) {
                                valid = false;
                            } else if (pPlay[0].getSuit() < play[0].getSuit()) {
                                valid = true;
                            } else if (play[4].getValue() > pPlay[4].getValue()) {
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


    public int getCardNo() {
        return cardNo;
    }

    public int getpCardNo() {
        return pCardNo;
    }

    public void addCardToPlay(Card card) {
        if (cardNo < 5) {
            play[cardNo] = card;
            cardNo++;
        }
    }

    public void addCardToPreviousPlay(Card card) {
        if (cardNo < 5) {
            pPlay[pCardNo] = card;
            pCardNo++;
        }
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
                if (play[j + 1].getIndex() < play[j].getIndex())
                {
                    temp = play[j];
                    play[j] = play[j + 1];
                    play[j + 1] = temp;
                    flag = true;
                }

            }
        }
        temp = null;
    }

    public Card[] release() {
        return play;
    }

    public Card getPlayCardByIndex(int index){
        return play[index];
    }

    public Card getPreviousPlayCardByIndex(int index){
        return pPlay[index];
    }

    public void clearPlay() {
        for (int i = 0; i < 5; i++) {
            play[i] = null;
        }
    }
    public void clearPreviousPlay(){
        for (int i = 0; i < 5; i++) {
            pPlay[i] = null;
        }
    }
}
