import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer {
	private Deck dealer;
	private int cardNo;
	
	public Dealer() {
		dealer = new Deck();
	}
	
	public void CreateDeck() {
		dealer.CreateDeck();
	}
	
	public void shuffle(){
		List<Integer> numbers = new ArrayList<Integer>(10); 
		for (int i = 0; i < 51; i++) {
		  numbers.add(i);
		}
		Collections.shuffle(numbers);
		for (int i = 0; i < 51; i++) {
			int r = numbers.get(i);
			Card temp = dealer.getCard(i);
			dealer.setCard(dealer.getCard(r), i);
			dealer.setCard(temp, r);
		}
	}
	
	public void deal(Hand hand) {
		hand.add(dealer.getCard(cardNo));
			cardNo++;
		if(cardNo == 51)
			cardNo = 0;
		
	}
}
