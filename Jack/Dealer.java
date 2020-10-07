package groupProj;
public class Dealer {
	Dealer dealer;
	
	public Dealer() {
		dealer = new Deck;
	}
	
	public void Dealer::CreateDeck() {
		dealer.CreateDeck();
	}
	
	public void shuffle(){
		srand((unsigned int)time(NULL));
			for (int i = 0; i < 51; i++) {
				int r = i + (rand() % (52 - i));
				Card^ temp = dealer->getCard(i);
				dealer->setCard(dealer->getCard(r), i);
				dealer->setCard(temp, r);
			}
	}
	
	public void deal(Hand hand) {
		hand->add(dealer->getCard(cardNo));
			cardNo++;
		if(cardNo == 51)
			cardNo = 0;
		
	}
}
