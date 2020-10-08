public class Controller {
	private Card card;
	private Dealer dealer;
	private Hand[] hand;
	private Play[] play;
	private boolean[] start;
	private int count;

	public GameController() {
		card = new Card();
		start = new boolean[4];
		for (int i = 0; i < 4; i++) {
			start[i] = false;
		}

		hand = new Hand[4];
		for (int i = 0; i < 4; i++) {
			hand[i] = new Hand();
		}

		play = new Play[4];
		for (int i = 0; i < 4; i++) {
			play[i] = new Play();
		}
		count = 0;
	}

	public void Deal(){
		dealer.CreateDeck();
		dealer.shuffle();
		for(int i = 0;i < 13;i++)
			dealer.deal(hand[0]);
		for(int i = 0;i < 13;i++)
			dealer.deal(hand[1]);
		for(int i = 0;i < 13;i++)
			dealer.deal(hand[2]);
		for(int i = 0;i < 13;i++)
			dealer.deal(hand[3]);
		hand[0].Sort();
		hand[1].Sort();
		hand[2].Sort();
		hand[3].Sort();
	}

	public int getIndex(int i, int p){
		card = hand[p].getCard(i);
		return card.getIndex();
	}

	public void setStart(){
		for (int i = 0; i < 13; i++) {
			card = hand[0].getCard(i);
			if (card.getIndex() == 0) {
				start[0] = true;
			}
		}
		for (int i = 0; i < 13; i++) {
			card = hand[1].getCard(i);
			if (card.getIndex() == 0) {
				start[1] = true;
			}

		}for (int i = 0; i < 13; i++) {
			card = hand[2].getCard(i);
			if (card.getIndex() == 0) {
				start[2] = true;
			}

		}for (int i = 0; i < 13; i++) {
			card = hand[3].getCard(i);
			if (card.getIndex() == 0) {
				start[3] = true;
			}
		}
	}

	public boolean getStart(int p){
		return start[p];
	}

	public void addPlay(int i, int p, boolean b){
		if(b){
			play[p].add(hand[p].getCard(i));
		}
	}

	public void addpPlay(int p, int P){
		for(int i = 0;i<play[P].getNoCard();i++)
			play[p].addPreviousPlay(play[P].getPlay(i));
	}

	public void pPlayAddpPlay(int p, int P){
		for(int i = 0;i<play[P].getpNoCard();i++)
			play[p].addPreviousPlay(play[P].getPPlay(i));
	}

	public boolean isValidPlay(int p){
		play[p].Sort();
		boolean valid;
		valid = play[p].isValidPlay();
		return valid;
	}

	public void clearPlay(int p){
		play[p].clear();
	}

	public void clearpPlay(int p){
		play[p].clearp();
	}

	public int getNoCard(int p){
		return hand[p].getNoCard();
	}

	public void cardNoDe(int p){
		hand[p].cardNoDe();
	}
}