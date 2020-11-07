
public class Player {
    private Hand hand;
    private Play play;
    private String name;

    public Player() {
        hand = new Hand();
        play = new Play();
        name = "";
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

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }


    @Override
    public String toString() {
        return "{ name=" + name +
                // " ,hand={" + hand.toString() +
                " }\n" + " ,play={"  + play.toString() +
                " } \n";
    }
}
