
public class HumanPlayer extends AbstractPlayer  {

    public HumanPlayer() {
        super();
        sortStrategy = new BubbleSort();
    }

    public HumanPlayer(Hand hand, Play play, String name, SortStrategy sortStrategy) {
        super(hand, play, name, sortStrategy);
    }

}