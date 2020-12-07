package big2pokergame;

public abstract class AbstractPlayer {
    protected Hand hand;
    protected Play play;
    protected String name;
    protected SortStrategy sortStrategy = null;

    public AbstractPlayer() {
        hand = new Hand();
        play = new Play();
        name = "";
        this.sortStrategy = new BubbleSort();
    }

    public void sort() {
        sortStrategy.sort(hand);
    }

    public AbstractPlayer(Hand hand, Play play, String name, SortStrategy sortStrategy) {
        this.hand = hand;
        this.play = play;
        this.name = name;
        this.sortStrategy = sortStrategy;
    }

    public boolean win() {
        if (getHand().getCardNo() == 0)
            return true;
        else
            return false;
    }

    public Hand getHand() {
        return hand;
    }

    public Play getPlay() {
        return play;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{ name=" + name +
        // " ,hand={" + hand.toString() +
                " }\n" + " ,play={" + play.toString() + " } \n";
    }
}
