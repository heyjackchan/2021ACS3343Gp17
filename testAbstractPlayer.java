import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testAbstractPlayer {
	private class JackPlayer extends AbstractPlayer {
	    public JackPlayer(Hand hand, Play play, String name, SortStrategy sortStrategy) {
	        super(hand, play, name, sortStrategy);
	    }
	}
	
	//Test case A0501: Create an new player with All card of club in descending order, apply sorting and print out its information.
	@Test
	public void test_NewPlayer_All3Club_sort(){
		Hand hand = new Hand();
		for(int i=0;i<13;i++) {
			hand.addCard(new Card((12-i),1));
		}
		Play play = new Play();
		String name = "Jack";
		SortStrategy sortStrategy = new BubbleSort();
		JackPlayer jackPlayer = new JackPlayer(hand, play, name, sortStrategy);
		jackPlayer.sort();
		System.out.print(hand.toString());
		String expectResult = "\n"
				+ "Card{index= 1, value= 0, suit= 1, discarded= false, CardSymbol= C3}\n"
				+ "Card{index= 5, value= 1, suit= 1, discarded= false, CardSymbol= C4}\n"
				+ "Card{index= 9, value= 2, suit= 1, discarded= false, CardSymbol= C5}\n"
				+ "Card{index= 13, value= 3, suit= 1, discarded= false, CardSymbol= C6}\n"
				+ "Card{index= 17, value= 4, suit= 1, discarded= false, CardSymbol= C7}\n"
				+ "Card{index= 21, value= 5, suit= 1, discarded= false, CardSymbol= C8}\n"
				+ "Card{index= 25, value= 6, suit= 1, discarded= false, CardSymbol= C9}\n"
				+ "Card{index= 29, value= 7, suit= 1, discarded= false, CardSymbol= CX}\n"
				+ "Card{index= 33, value= 8, suit= 1, discarded= false, CardSymbol= CJ}\n"
				+ "Card{index= 37, value= 9, suit= 1, discarded= false, CardSymbol= CQ}\n"
				+ "Card{index= 41, value= 10, suit= 1, discarded= false, CardSymbol= CK}\n"
				+ "Card{index= 45, value= 11, suit= 1, discarded= false, CardSymbol= CA}\n"
				+ "Card{index= 49, value= 12, suit= 1, discarded= false, CardSymbol= C2}\n"
				+ "";
		String actualResult = hand.toString();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case A0502: Create an new player without any card, test win function.
	@Test
	public void test_NewPlayer_winTRUE(){
		Hand hand = new Hand();
		Play play = new Play();
		String name = "Jack";
		SortStrategy sortStrategy = new BubbleSort();
		JackPlayer jackPlayer = new JackPlayer(hand, play, name, sortStrategy);
		boolean expectResult = true;
		boolean actualResult = jackPlayer.win();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case A0503: Create an new player with All card of club in descending order, test win function.
	@Test
	public void test_NewPlayer_winFALSE(){
		Hand hand = new Hand();
		for(int i=0;i<13;i++) {
			hand.addCard(new Card((12-i),1));
		}
		Play play = new Play();
		String name = "Jack";
		SortStrategy sortStrategy = new BubbleSort();
		JackPlayer jackPlayer = new JackPlayer(hand, play, name, sortStrategy);
		boolean expectResult = false;
		boolean actualResult = jackPlayer.win();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case A0504: Create an new player with All card of club in descending order, test getHand function.
	@Test
	public void test_NewPlayer_All3Club_getHand(){
		Hand hand = new Hand();
		for(int i=0;i<13;i++) {
			hand.addCard(new Card((12-i),1));
		}
		Play play = new Play();
		String name = "Jack";
		SortStrategy sortStrategy = new BubbleSort();
		JackPlayer jackPlayer = new JackPlayer(hand, play, name, sortStrategy);
		Hand expectResult = hand;
		Hand actualResult = jackPlayer.getHand();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case A0505: Create an new player with All card of club in descending order, test getPlay function.
	@Test
	public void test_NewPlayer_All3Club_getPlay(){
		Hand hand = new Hand();
		for(int i=0;i<13;i++) {
			hand.addCard(new Card((12-i),1));
		}
		Play play = new Play();
		String name = "Jack";
		SortStrategy sortStrategy = new BubbleSort();
		JackPlayer jackPlayer = new JackPlayer(hand, play, name, sortStrategy);
		Play expectResult = play;
		Play actualResult = jackPlayer.getPlay();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case A0506: Create an new player with All card of club in descending order, test getName function.
	@Test
	public void test_NewPlayer_All3Club_getName(){
		Hand hand = new Hand();
		for(int i=0;i<13;i++) {
			hand.addCard(new Card((12-i),1));
		}
		Play play = new Play();
		String name = "Jack";
		SortStrategy sortStrategy = new BubbleSort();
		JackPlayer jackPlayer = new JackPlayer(hand, play, name, sortStrategy);
		String expectResult = "Jack";
		String actualResult = jackPlayer.getName();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case A0507: Create an new player with All card of club in descending order, test setName function.
	@Test
	public void test_NewPlayer_All3Club_setName(){
		Hand hand = new Hand();
		for(int i=0;i<13;i++) {
			hand.addCard(new Card((12-i),1));
		}
		Play play = new Play();
		String name = "Jack";
		SortStrategy sortStrategy = new BubbleSort();
		JackPlayer jackPlayer = new JackPlayer(hand, play, name, sortStrategy);
		jackPlayer.setName("David");
		String expectResult = "David";
		String actualResult = jackPlayer.getName();
		assertEquals(expectResult, actualResult);
	}

}