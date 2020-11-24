import java.util.Scanner;
import java.util.ArrayList;

public class GameController {
    private Dealer dealer;
    private AbstractPlayer[] players;
    private AbstractPlayer currentPlayer = null;
    private AbstractPlayer nextPlayer = null;
    private int currentPlayerIndex;
    private int round = 1;
    private int skipCount = 1;
    private int turn = 1;
    private boolean isFirstPlayer = true;
    private boolean endGame = false;
    private Scanner sc = new Scanner(System.in);
    private ArrayList<History> histories = new ArrayList<History>();
    private static final GameController gameController = new GameController();

    private GameController() {
        dealer = new Dealer();
        players = new HumanPlayer[4];
        for (int i = 0; i < 4; i++) {
            players[i] = new HumanPlayer();
        }
        currentPlayerIndex = -1;
    }

    public static GameController getInstance(){
        return gameController;
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
            players[i].sort();
            // System.out.println("Player " + (i + 1) + " {\n"  + players[i].toString());

        }
        System.out.println("                                                                         \r\n"
        		+ "                                                                         \r\n"
        		+ "BBBBBBBBBBBBBBBBB     iiii                            222222222222222    \r\n"
        		+ "B::::::::::::::::B   i::::i                          2:::::::::::::::22  \r\n"
        		+ "B::::::BBBBBB:::::B   iiii                           2::::::222222:::::2 \r\n"
        		+ "BB:::::B     B:::::B                                 2222222     2:::::2 \r\n"
        		+ "  B::::B     B:::::Biiiiiii    ggggggggg   ggggg                 2:::::2 \r\n"
        		+ "  B::::B     B:::::Bi:::::i   g:::::::::ggg::::g                 2:::::2 \r\n"
        		+ "  B::::BBBBBB:::::B  i::::i  g:::::::::::::::::g              2222::::2  \r\n"
        		+ "  B:::::::::::::BB   i::::i g::::::ggggg::::::gg         22222::::::22   \r\n"
        		+ "  B::::BBBBBB:::::B  i::::i g:::::g     g:::::g        22::::::::222     \r\n"
        		+ "  B::::B     B:::::B i::::i g:::::g     g:::::g       2:::::22222        \r\n"
        		+ "  B::::B     B:::::B i::::i g:::::g     g:::::g      2:::::2             \r\n"
        		+ "  B::::B     B:::::B i::::i g::::::g    g:::::g      2:::::2             \r\n"
        		+ "BB:::::BBBBBB::::::Bi::::::ig:::::::ggggg:::::g      2:::::2       222222\r\n"
        		+ "B:::::::::::::::::B i::::::i g::::::::::::::::g      2::::::2222222:::::2\r\n"
        		+ "B::::::::::::::::B  i::::::i  gg::::::::::::::g      2::::::::::::::::::2\r\n"
        		+ "BBBBBBBBBBBBBBBBB   iiiiiiii    gggggggg::::::g      22222222222222222222\r\n"
        		+ "                                        g:::::g                          \r\n"
        		+ "                            gggggg      g:::::g                          \r\n"
        		+ "                            g:::::gg   gg:::::g                          \r\n"
        		+ "                             g::::::ggg:::::::g                          \r\n"
        		+ "                              gg:::::::::::::g                           \r\n"
        		+ "                                ggg::::::ggg                             \r\n"
        		+ "                                   gggggg                                \r\n"
        		+ "  ____    U  ___ u   _  __  U _____ u   ____                   ____      _      __  __  U _____ u \r\n"
        		+ "U|  _\"\\ u  \\/\"_ \\/  |\"|/ /  \\| ___\"|/U |  _\"\\ u             U /\"___|uU  /\"\\  uU|' \\/ '|u\\| ___\"|/ \r\n"
        		+ "\\| |_) |/  | | | |  | ' /    |  _|\"   \\| |_) |/             \\| |  _ / \\/ _ \\/ \\| |\\/| |/ |  _|\"   \r\n"
        		+ " |  __/.-,_| |_| |U/| . \\\\u  | |___    |  _ <                | |_| |  / ___ \\  | |  | |  | |___   \r\n"
        		+ " |_|    \\_)-\\___/   |_|\\_\\   |_____|   |_| \\_\\                \\____| /_/   \\_\\ |_|  |_|  |_____|  \r\n"
        		+ " ||>>_       \\\\   ,-,>> \\\\,-.<<   >>   //   \\\\_               _)(|_   \\\\    >><<,-,,-.   <<   >>  \r\n"
        		+ "(__)__)     (__)   \\.)   (_/(__) (__) (__)  (__)             (__)__) (__)  (__)(./  \\.) (__) (__) \r\n\r\n\r\n"
        		+ "******************************************************************************************************\r\n\r\n");
    }

    public void promptPlayerName() {
        int index = 1;
        for (AbstractPlayer player: players) {
            System.out.print("Please enter player " + index +" Name: ");
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
        String handStr = "Cards that you have: \n";
        String line1 = ".------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.";
        String line2 = "";
        String line3 = "";
        String line4 = "";
        String line5 = "";
        String line6 = "'------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'";
        String line8 = "";
        String line9 = "|------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|";
        for (int i = 0; i < 13; i++) {
            Card temp = players[index].getHand().getCardByIndex(i);
            if (!temp.isDiscarded()) {
            	int j = i + 1;
                if (i == 12) {
                	line8 += "|  " + j + "  |";
                	switch (temp.getSuit()) {
                	case 0:
                    	line2 += "|" + temp.getValueSymbol() + " /\\  |";
                    	line3 += "| /  \\ |";
                    	line4 += "| \\  / |";
                    	line5 += "|  \\/ " + temp.getValueSymbol() + "|";
                    	break;
                	case 1:
                    	line2 += "|" + temp.getValueSymbol() + " _   |";
                    	line3 += "| ( )  |";
                    	line4 += "|(_x_) |";
                    	line5 += "|  Y  " + temp.getValueSymbol() + "|";
                    	break;
                	case 2:
                    	line2 += "|" + temp.getValueSymbol() + "_  _ |";
                    	line3 += "|( \\/ )|";
                    	line4 += "| \\  / |";
                    	line5 += "|  \\/ " + temp.getValueSymbol() + "|";
                    	break;
                	case 3:
                    	line2 += "|" + temp.getValueSymbol() + " .   |";
                    	line3 += "| / \\  |";
                    	line4 += "|(_,_) |";
                    	line5 += "|  I  " + temp.getValueSymbol() + "|";
                    	break;
                	default:
                    	line2 += "";
                    	line3 += "";
                    	line4 += "";
                    	line5 += "";
                    	break;
                	}
                } else {
                	if(i<9) {
                		line8 += "|   " + j + "  |     ";
                	}else {
                		line8 += "|  " + j + "  |     ";
                	}
                	switch (temp.getSuit()) {
                	case 0:
                    	line2 += "|" + temp.getValueSymbol() + " /\\  |     ";
                    	line3 += "| /  \\ |     ";
                    	line4 += "| \\  / |     ";
                    	line5 += "|  \\/ " + temp.getValueSymbol() + "|     ";
                    	break;
                	case 1:
                    	line2 += "|" + temp.getValueSymbol() + " _   |     ";
                    	line3 += "| ( )  |     ";
                    	line4 += "|(_x_) |     ";
                    	line5 += "|  Y  " + temp.getValueSymbol() + "|     ";
                    	break;
                	case 2:
                    	line2 += "|" + temp.getValueSymbol() + "_  _ |     ";
                    	line3 += "|( \\/ )|     ";
                    	line4 += "| \\  / |     ";
                    	line5 += "|  \\/ " + temp.getValueSymbol() + "|     ";
                    	break;
                	case 3:
                    	line2 += "|" + temp.getValueSymbol() + " .   |     ";
                    	line3 += "| / \\  |     ";
                    	line4 += "|(_,_) |     ";
                    	line5 += "|  I  " + temp.getValueSymbol() + "|     ";
                    	break;
                	default:
                    	line2 += "";
                    	line3 += "";
                    	line4 += "";
                    	line5 += "";
                    	break;
                	}
                }
            } else {
                if (i == 12) {
                	line8 += "|  XX  |";
                	line2 += "|######|";
                	line3 += "|######|";
                	line4 += "|######|";
                	line5 += "|######|";
                } else {
                	line8 += "|  XX  |     ";
                	line2 += "|######|     ";
                	line3 += "|######|     ";
                	line4 += "|######|     ";
                	line5 += "|######|     ";
                }
            }
        }
        handStr += line1 + "\n" + line2 + "\n" + line3 + "\n" + line4 + "\n" + line5 + "\n" + line6 + "\n\n" + line8 + "\n" + line9 + "\n";
        System.out.println(handStr);
    }

//    private void printCardIndex() {
//        String indexStr = "Card index: \t\t";
//        for (int i = 0; i < 13; i++) {
//            indexStr += (i + 1) + "\t";
//        }
//        System.out.println(indexStr);
//    }

    private String promptPlayerInput() {
        System.out.print("Input the card index(s) to indicate the card you want to play (Input 0 to discard this round):");
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
        History history = new History(round, turn, playerName, currentPlay);
        histories.add(history);
    }

    private void printHistory(History history) {
        System.out.println("*[Round " + history.getRound() +"] [Turn "+ history.getTurn() + "] [" + history.getPlayerName() + "]: " + history.printCurrentPlay());
    }

    private void printHistories() {
        if (histories.size() == 0) {
            System.out.println("****************************************************");
            System.out.println("No History");
            System.out.println("****************************************************");
        } else {
            System.out.println("****************************************************");
            System.out.println("history of discarded card: ");
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
        pause();
    }

    private int nextPlayerIndex() {
        return (currentPlayerIndex + 1) % 4;
    }

    private boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++)
            if (Character.isDigit(s.charAt(i)) == false)
                return false;
        return true;
    }

    private boolean handleDiscardCards(String input) {
        Play currentPlay = currentPlayer.getPlay();
        Play nextPlay = nextPlayer.getPlay();
        Hand currentHand = currentPlayer.getHand();
        String[] indexes = input.split(" ");
        for (String index: indexes){
            if(isNumber(index)){
                int i = Integer.parseInt(index) - 1;
                if(i < 0 || i > 12) {
                    return false;
                }
                if(currentHand.getCardByIndex(i).isDiscarded()){
                    return false;
                }
            } else {
                return false;
            }

        }
        for (String index: indexes){
            int i = Integer.parseInt(index) - 1;
            currentPlay.getCurrentCombination().addCard(currentHand.getCardByIndex(i));
        }
        currentPlay.sort();
        // System.out.print("After sort hand: " + currentPlayer.toString());
        if(currentPlay.isValidPlay()){
            addHistory(currentPlayer.getName(), currentPlay.releaseCurrentCombination());
            for (String index: indexes){
                int i = Integer.parseInt(index) - 1;
                nextPlay.getPreviousCombination().addCard(currentHand.getCardByIndex(i));
                currentHand.releaseCard(i);
            }
            currentPlay.getCurrentCombination().clear();
            // System.out.print("After clear current play when play is valid: \n" + currentPlayer.toString());
            currentPlayer.getPlay().getPreviousCombination().clear();
            // System.out.print("After clear previous play(current player) : \n" + currentPlayer.toString());
            // System.out.print("After clear previous play(next player): \n" + nextPlayer.toString());
            return true;
        } else {
            currentPlay.getCurrentCombination().clear();
            // System.out.print("After clear current play when play is not valid: \n" + currentPlayer.toString());
            return false;
        }
    }

    private void handleSkipPlay(){
        Combination currentPreviousPlay = currentPlayer.getPlay().getPreviousCombination();
        Combination nextPreviousPlay = nextPlayer.getPlay().getPreviousCombination();
        for (int i = 0; i < currentPreviousPlay.getCardNo(); i++) {
            nextPreviousPlay.addCard(currentPreviousPlay.getCardByIndex(i));
        }
        currentPreviousPlay.clear();
        // System.out.print("After clear previous play when Skip: \n" + currentPlayer.toString());
    }

    private boolean checkMenuInput(String input) {
        if (input.equals("0") || input.equals("1") || input.equals("2")) {
            return true;
        } else {
            return false;
        }
    }

    private void printRoundInfo() {
        System.out.println("Now is turn "+ turn + " of round " + round +"!");
        System.out.println("It is your turn, "+ players[currentPlayerIndex].getName() +": ");
    }

    private void pause(){
        System.out.println("Press Enter Key To Continue...");
        new java.util.Scanner(System.in).nextLine();
    }

    private void handleTurn() {
        currentPlayer = players[currentPlayerIndex];
        nextPlayer = players[nextPlayerIndex()];
        System.out.println("What is your next action?\n0. Discard this round\n1. Play card(s)\n2. View all history");
        printPlayerHand(currentPlayerIndex);
        //printCardIndex();
        System.out.print("Please select your action: ");
        String menuInput = sc.nextLine();
        while (!checkMenuInput(menuInput)) {
            System.out.println("Incorrect input! Please enter a correct action number!");
            System.out.print("Please select your action: ");
            menuInput = "";
            menuInput = sc.nextLine();
        }
        switch(menuInput) {
            case "0":
                skip();
                break;
            case "1":
                discard();
                break;
            case "2":
                printHistories();
                break;
            default:
                System.out.println("Incorrect input! Please enter a correct action number!");
        }

        clearScreen();
    }

    private void incrementRound() {
        if(turn > 4){
            round++;
            turn = 1;
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
                    currentPlayer.getPlay().getPreviousCombination().clear();
                    // System.out.print("After clear previous play when come back: \n" + currentPlayer.toString());
                }
                if (handleDiscardCards(playerInput)) {
                    isFirstPlayer = false;
                    skipCount = 1;
                    if (currentPlayer.win()) {
                        printWinningMessages();
                        endGame = true;
                    } else {
                        currentPlayerIndex = nextPlayerIndex();
                        turn++;
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
        if (skipCount < 4 && !isFirstPlayer) {
            addHistory(currentPlayer.getName(), null);
            handleSkipPlay();
            currentPlayerIndex = nextPlayerIndex();
            skipCount++;
            turn++;
        } else {
            System.out.println("Your cannot skip! Please choose again!");
            pause();
        }
        incrementRound();
    }

    private void printLastHistory() {
        if (histories.size() == 0) {
            System.out.println("****************************************************");
            System.out.println("*No History");
            System.out.println("****************************************************");
        } else {
            System.out.println("****************************************************");
            System.out.println("*History of last rounds' discarded card:");
            for(int i = histories.size() - 1; i >= 0; i--) {
                if(histories.get(i).printCurrentPlay().equals("SKIP")) {
                    continue;
                } else {
                    printHistory(histories.get(i));
                    break;
                }
            }
            System.out.println("****************************************************");
        }
    }

    public void play() {
        while(!endGame) {
            printRoundInfo();
            printRemainingNumberOfCards();
            printLastHistory();
            handleTurn();
        }
        sc.close();
    }
}
