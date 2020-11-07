
public class History {
    private int round;
    private String playerName;
    private Combination currentPlay;

    public History() {
        round = 0;
        playerName = null;
        currentPlay = null;
    }

    public History(int round, String playerName, Combination currentPlay) {
        this.round = round;
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

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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
