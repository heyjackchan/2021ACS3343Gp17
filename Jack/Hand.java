import java.util.ArrayList;
import java.util.List;

public class Hand {
	private ArrayList<Card> hand;
	private int cardNo;
	
	public Hand() {
		hand = new ArrayList<Card>();
		for(int i=0; i < 13;i++) {
			Card c = new Card();
			hand.add(c);
		}
		cardNo = 0;
		
	}
	
	void Sort() {
		boolean flag = true;
		Card temp;
		for(int i = 1; (i<=13) && flag;i++) {
			flag = false;
			for(int j= 0; j<12 ; j++) {
				if (hand.get(j+1).getindex() < hand.get(j).getindex()) {
					temp = hand.get(j);
					hand.set(j, hand.get(j+1));
					hand.set(j+1,temp);
					flag = true;
				}
			}
		}
		
	}
	
	void Add(Card card) {
		if(cardNo < 13 ) {
			hand.set(cardNo, card);
			cardNo++;
		}
		
	}
	
	Card getCard(int n) {
		
		return hand.get(n);
	}
	
	void SetCard(Card card, int n) {
		hand.set(n, card);
	}
	
	int getNoCard() {
		return cardNo;
	}
	
	Card release(int v, char s) {
		Card card = null;
		for(int i = 0; i<13; i++) {
			if(hand.get(i).getValue() == v 
					&& hand.get(i).getSuit() == s) {
				card = hand.get(i);
				break;
			}
		}
		return card;
	}
	
	void cardNoDe() {
		cardNo --;
	}
	
	void clear() {
		Card card = new Card();
		for (int i = 0; i < 13; i++) {
			hand.set(i,card);
		}
		cardNo = 0;
		
	};
	
}
