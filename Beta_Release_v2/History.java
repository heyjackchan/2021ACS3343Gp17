
public class History {
    private int round;
    private int turn;
    private String playerName;
    private Combination currentPlay;

    public History(int round, int turn, String playerName, Combination currentPlay) {
        this.round = round;
        this.turn = turn;
        this.playerName = playerName;
        if(currentPlay == null){
            this.currentPlay = null;
        }else {
            this.currentPlay = new Combination(currentPlay);
        }
    }

    public int getRound() {
        return round;
    }

    public int getTurn() {
        return turn;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String printCurrentPlay() {
        if(currentPlay == null) {
            return "SKIP";
        } else {
            String playStr = "";
            for (int i = 0; i < currentPlay.getCardNo(); i++) {
                if (i == currentPlay.getCardNo() - 1) {
                    playStr += currentPlay.getCardByIndex(i).printCardSymbol();
                } else {
                    playStr += currentPlay.getCardByIndex(i).printCardSymbol() + ", ";
                }
            }
            return playStr;
        }
    }


}
