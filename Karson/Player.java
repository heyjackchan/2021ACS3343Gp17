public class Player {
    private Hand hand;
    private Play play;
    private boolean start;
    private int penaltyScore;

    public Player() {
        hand = new Hand();
        play = new Play();
        start = false;
        penaltyScore = 0;
    }

    public Player(Hand hand, Play play, int penaltyScore) {
        this.hand = hand;
        this.play = play;
        this.penaltyScore = penaltyScore;
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

    public void setStart(boolean start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "hand={" + hand.toString() +
                ", start=" + start +
                ", penaltyScore=" + penaltyScore +
                '}';
    }
}
