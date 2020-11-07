
public class PokerGame {
    public static void main(String[] args) {
        GameController gc = new GameController();
        gc.prepareGame();
        gc.promptPlayerName();
        gc.play();
    }
}
