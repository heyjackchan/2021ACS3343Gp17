package big2pokergame;

public class PokerGame {
    public static void main(String[] args) {
        GameController gc = GameController.getInstance();
        gc.prepareGame();
        gc.promptPlayerName();
        gc.play();
    }
}
