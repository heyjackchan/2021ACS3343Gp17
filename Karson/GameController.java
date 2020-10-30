import java.util.Scanner;

public class GameController {
    private Dealer dealer;
    private Player[] players;
    private int startPlayerIndex;

    public GameController() {
        dealer = new Dealer();
        players = new Player[4];
        for (int i = 0; i < 4; i++) {
            players[i] = new Player();
        }
        startPlayerIndex = -1;
    }

    public GameController(Dealer dealer, Player[] players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void prepareGame() {
        dealer.createDeck();
        dealer.shuffle();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                dealer.deal(players[i].getHand());
                int startIndex = players[i].getHand().getCardByIndex(j).getIndex();
                if (startIndex == 0) {
                    players[i].setStart(true);
                    startPlayerIndex = i;
                }
            }
            // System.out.println("Player " + (i + 1) + "{\n" + players[i].toString());
            players[i].getHand().sort();
            // System.out.println("Player " + (i + 1) + " {\n"  + players[i].toString());

        }
        System.out.println("Game Start!!");
    }

    public void printPlayerHand(int index) {
        String handStr = "Player "+ (index + 1) + "'s hand: ";
        for (int i = 0; i < 13; i++) {
            Card temp = players[index].getHand().getCardByIndex(i);
            if (!temp.isPlayed()) {
                if (i == 12) {
                    handStr += String.valueOf(Card.printSuit(temp.getSuit())) + String.valueOf(Card.printValue(temp.getValue()));
                } else {
                    handStr += String.valueOf(Card.printSuit(temp.getSuit())) + String.valueOf(Card.printValue(temp.getValue())) + ", ";
                }
            } else {
                if (i == 12) {
                    handStr += "--";
                } else {
                    handStr += "--, ";
                }
            }
        }
        System.out.println(handStr);
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            Player currentPlayer = players[startPlayerIndex];
            printPlayerHand(startPlayerIndex);
            System.out.println("Please select your play: ");
            String input = sc.nextLine();
            String[] indexes = input.split(" ");
            for (String index: indexes){
                int i = Integer.parseInt(index);
                currentPlayer.getHand().releaseCard(i);
            }
            if (currentPlayer.win()) {
                break;
            } else {
                startPlayerIndex = (startPlayerIndex + 1) % 4;
            }
        }
        sc.close();
    }
}
