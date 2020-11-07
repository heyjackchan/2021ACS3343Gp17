import java.util.Scanner;
import java.util.ArrayList;

public class GameController {
    private Dealer dealer;
    private Player[] players;
    private Player currentPlayer = null;
    private Player nextPlayer = null;
    private int currentPlayerIndex;
    private int round = 1;
    private int skipCount = 1;
    private int roundCount = 0;
    private boolean endGame = false;
    private Scanner sc = new Scanner(System.in);
    private ArrayList<History> histories = new ArrayList<History>();

    public GameController() {
        dealer = new Dealer();
        players = new Player[4];
        for (int i = 0; i < 4; i++) {
            players[i] = new Player();
        }
        currentPlayerIndex = -1;
    }

    public void prepareGame() {
        dealer.shuffle();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                dealer.deal(players[i].getHand());
                int startIndex = players[i].getHand().getCardByIndex(j).getIndex();
                if (startIndex == 0) {
                    currentPlayerIndex = i;
                }
            }
            // System.out.println("Player " + (i + 1) + "{\n" + players[i].toString());
            players[i].getHand().sort();
            // System.out.println("Player " + (i + 1) + " {\n"  + players[i].toString());

        }
        System.out.println("Game Start!!!");
    }


    public void promptPlayerName() {
        int index = 1;
        for (Player player: players) {
            System.out.print("Player " + index +" Name: ");
            player.setName(sc.nextLine());
            index++;
        }
        clearScreen();
    }

    private static void clearScreen() {
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }catch(Exception e) {
            System.out.println(e);
        }
    }


    private void printPlayerHand(int index) {
        String handStr = "Cards that you have: \t";
        for (int i = 0; i < 13; i++) {
            Card temp = players[index].getHand().getCardByIndex(i);
            if (!temp.isDiscarded()) {
                if (i == 12) {
                    handStr += temp.printCardSymbol();
                } else {
                    handStr += temp.printCardSymbol() + "\t";
                }
            } else {
                if (i == 12) {
                    handStr += "--";
                } else {
                    handStr += "--\t";
                }
            }
        }
        System.out.println(handStr);
    }

    private void printCardIndex() {
        String indexStr = "Card index: \t\t";
        for (int i = 0; i < 13; i++) {
            indexStr += (i + 1) + "\t";
        }
        System.out.println(indexStr);
    }

    private String promptPlayerInput() {
        System.out.print("Input the card index(s) to indicate the card you want to play (Input 0 to your turn):");
        String input = sc.nextLine();
        return input;
    }


    private void printRemainingNumberOfCards() {
        for (int i = 0; i < 4; i++) {
            if (i != currentPlayerIndex) {
                System.out.println(players[i].getName() + " still have " + players[i].getHand().getCardNo() + " card(s).");
            }
        }
    }

    private void addHistory(String playerName, Combination currentPlay) {
        History history = new History(round, playerName, currentPlay);
        histories.add(history);
    }

    private void printHistory(History history) {
        System.out.println("[Round " + history.getRound() +"] [" + history.getPlayerName() + "]: " + history.printCurrentPlay());
    }

    private void printHistories() {
        if (histories.size() == 0) {
            System.out.println("****************************************************");
            System.out.println("No History");
            System.out.println("****************************************************");
        } else {
            System.out.println("****************************************************");
            System.out.println("History of discarded card: ");
            for (History history : histories) {
                printHistory(history);
            }
            System.out.println("****************************************************");
        }
        discard();
    }

    private void printWinningMessages() {
        System.out.println("Congratulations " + currentPlayer.getName() + "! you win the game!");
        printRemainingNumberOfCards();
        System.out.println("Game End!!!");
    }

    private int nextPlayerIndex() {
        return (currentPlayerIndex + 1) % 4;
    }

    private boolean handleDiscardCards(String input) {
        Play currentPlay = currentPlayer.getPlay();
        Play nextPlay = nextPlayer.getPlay();
        Hand currentHand = currentPlayer.getHand();
        String[] indexes = input.split(" ");
        for (String index: indexes){
            int i = Integer.parseInt(index) - 1;
            currentPlay.getCurrentCombination().addCardToCombination(currentHand.getCardByIndex(i));
        }
        currentPlay.getCurrentCombination().sort();
        // System.out.print("After sort hand: " + currentPlayer.toString());
        if(currentPlay.isValidPlay()){
            addHistory(currentPlayer.getName(), currentPlay.releaseCurrentCombination());
            for (String index: indexes){
                int i = Integer.parseInt(index) - 1;
                nextPlay.getPreviousCombination().addCardToCombination(currentHand.getCardByIndex(i));
                currentHand.releaseCard(i);
            }
            currentPlay.getCurrentCombination().clearCombination();
            // System.out.print("After clear current play when play is valid: \n" + currentPlayer.toString());
            currentPlayer.getPlay().getPreviousCombination().clearCombination();
            // System.out.print("After clear previous play(current player) : \n" + currentPlayer.toString());
            // System.out.print("After clear previous play(next player): \n" + nextPlayer.toString());
            return true;
        } else {
            currentPlay.getCurrentCombination().clearCombination();
            // System.out.print("After clear current play when play is not valid: \n" + currentPlayer.toString());
            return false;
        }
    }

    private void handleSkipPlay(){
        Combination currentPreviousPlay = currentPlayer.getPlay().getPreviousCombination();
        Combination nextPreviousPlay = nextPlayer.getPlay().getPreviousCombination();
        for (int i = 0; i < currentPreviousPlay.getCardNo(); i++) {
            nextPreviousPlay.addCardToCombination(currentPreviousPlay.getCardByIndex(i));
        }
        currentPreviousPlay.clearCombination();
        // System.out.print("After clear previous play when Skip: \n" + currentPlayer.toString());
    }

    private boolean checkMenuInput(String input) {
        if (input.equals("1") || input.equals("2") || input.equals("3")) {
            return true;
        } else {
            return false;
        }
    }

    private void printMenu() {
        System.out.println("It is your turn, "+ players[currentPlayerIndex].getName() +": ");
        System.out.println("What is your next action?\n1. Play card(s)\n2. Skip\n3. View History");
        printPlayerHand(currentPlayerIndex);
        printCardIndex();
        System.out.print("Please select your action: ");
        String menuInput = sc.nextLine();
        while (!checkMenuInput(menuInput)) {
            System.out.println("Incorrect input! Please enter a correct action number!");
            System.out.print("Please select your action: ");
            menuInput = "";
            menuInput = sc.nextLine();
        }
        switch(menuInput) {
            case "1":
                discard();
                break;
            case "2":
                skip();
                break;
            case "3":
                printHistories();
                break;
            default:
                System.out.println("Incorrect input! Please enter a correct action number!");
        }

        clearScreen();
    }
    private void incrementRound() {
        if(roundCount == 4){
            round++;
            roundCount = 0;
        }
    }

    private void discard() {
        while (true) {
            String playerInput = promptPlayerInput();
            if(playerInput.equals("0")) {
                skip();
                break;
            } else {
                if (skipCount == 4) {
                    currentPlayer.getPlay().getPreviousCombination().clearCombination();
                    // System.out.print("After clear previous play when come back: \n" + currentPlayer.toString());
                }
                if (handleDiscardCards(playerInput)) {
                    skipCount = 1;
                    if (currentPlayer.win()) {
                        printWinningMessages();
                        endGame = true;
                    } else {
                        currentPlayerIndex = nextPlayerIndex();
                        roundCount++;
                    }
                    break;
                } else {
                    System.out.println("Your play is invalid! Please choose again!");
                }
                incrementRound();
            }
        }
    }

    private void skip() {
        if (skipCount < 4) {
            addHistory(currentPlayer.getName(), null);
            handleSkipPlay();
            currentPlayerIndex = nextPlayerIndex();
            skipCount++;
            roundCount++;
        } else {
            System.out.println("Your cannot skip! Please choose again!");
        }
        incrementRound();
    }

    public void play() {

        while(!endGame) {
            currentPlayer = players[currentPlayerIndex];
            nextPlayer = players[nextPlayerIndex()];
            printRemainingNumberOfCards();
            printMenu();
        }
        sc.close();
    }
}
