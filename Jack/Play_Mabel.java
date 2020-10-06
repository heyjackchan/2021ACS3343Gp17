public class Play {	
	private int pCardNo, cardNo = 0;
	private Card[] pPlay, play;
	
	//initiate and delete
	public Play() {
		pPlay = new Card[5];
		play = new Card[5];
	
		for(int i = 0; i < 5; i++) {
			Card e = new Card();
			pPlay[i] = e;
		}
		for(int i = 0; i < 5; i++) {
			Card e = new Card();
			play[i] = e;
		}
	}
	
	//Getter
	public int getNoCard() {
		return cardNo;
	}
	
	public int getpNoCard() {
		return pCardNo;
	}	
	
	public Card getPlay(int i) {
		return play[i];
	}
	
	public Card getPPlay(int i) {
		return pPlay[i];
	}
	
	//Flags
	public boolean addPreviousPlay(Card e) {
		if(pCardNo < 5) {
			pPlay[pCardNo] = e;
			pCardNo++;
		}
		return true;	
	}
	
	public boolean add(Card e) {
		if(cardNo < 5) {
			play[cardNo] = e;
			cardNo++;
		}
		return true;	
	}
	
	//Function
	public void sort() {
		boolean flag = true;
		Card temp;
		for(int i = 1; (i <= getNoCard())&& flag; i++) {
			flag = false;
			for(int j = 0; j < (getNoCard() - 1); j++) {
				if(play[j + 1].getindex() < play[j].getindex()) {
					temp = play[j];
					play[j] = play[j + 1];
					play[j + 1] = temp;
					flag = true;
				}
			}
		}
	}
	
	public Card[] release(){
		return play;
	}
	
	public void clear() {
		Card card = new Card();
		for(int i = 0; i < getNoCard(); i++) {
			play[i] = card;
		}
		cardNo = 0;
	}
	
	public void clearp() {
		Card card = new Card();
		for(int i = 0; i < getpNoCard(); i++) {
			pPlay[i] = card;
		}
		pCardNo = 0;
	}
}
