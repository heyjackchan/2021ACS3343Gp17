import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testAbstractPlayer {
	private class TestPlayer extends AbstractPlayer {
		//Create a new AbstractPlayer type TestPlayer.
	    public TestPlayer(Hand hand, Play play, String name, SortStrategy sortStrategy) {
	        super(hand, play, name, sortStrategy);
	    }
	}
	
	Hand hand = new Hand(); //Create a new hand.
	Play play = new Play(); //Create a new play.
	String name = "Jack"; //Create a new name.
	SortStrategy sortStrategy = new BubbleSort(); //Create a new sort method.
	
	//Test case UNIT0501: Create testPlayer to test sort() function.
	@Test
	public void testAbstractPlayer_TestPlayer_sort(){
		for(int i=0;i<13;i++) { //Add all 13 Club cards in descending order.
			hand.addCard(new Card((12-i),1));
		}
		TestPlayer testPlayer = new TestPlayer(hand, play, name, sortStrategy);
		testPlayer.sort();
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
	
	//Test case UNIT0502: Create testPlayer to test win() function in TRUE condition.
	@Test
	public void testAbstractPlayer_TestPlayer_winTRUE(){
		TestPlayer testPlayer = new TestPlayer(hand, play, name, sortStrategy);
		boolean expectResult = true;
		boolean actualResult = testPlayer.win();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case UNIT0503: Create testPlayer to test win() function in FALSE condition.
	@Test
	public void testAbstractPlayer_TestPlayer_winFALSE(){
		for(int i=0;i<13;i++) { //Add all 13 Club cards in descending order.
			hand.addCard(new Card((12-i),1));
		}
		TestPlayer testPlayer = new TestPlayer(hand, play, name, sortStrategy);
		boolean expectResult = false;
		boolean actualResult = testPlayer.win();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case UNIT0504: Create testPlayer to test getHand() function.
	@Test
	public void testAbstractPlayer_TestPlayer_getHand(){
		for(int i=0;i<13;i++) { //Add all 13 Club cards in descending order.
			hand.addCard(new Card((12-i),1));
		}
		TestPlayer testPlayer = new TestPlayer(hand, play, name, sortStrategy);
		Hand expectResult = hand;
		Hand actualResult = testPlayer.getHand();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case UNIT0505: Create testPlayer to test getPlay() function.
	@Test
	public void testAbstractPlayer_TestPlayer_getPlay(){
		TestPlayer testPlayer = new TestPlayer(hand, play, name, sortStrategy);
		Play expectResult = play;
		Play actualResult = testPlayer.getPlay();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case UNIT0506: Create testPlayer to test getName() function.
	@Test
	public void testAbstractPlayer_TestPlayer_getName(){
		TestPlayer testPlayer = new TestPlayer(hand, play, name, sortStrategy);
		String expectResult = "Jack";
		String actualResult = testPlayer.getName();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case UNIT0507: Create testPlayer to test setName() function.
	@Test
	public void testAbstractPlayer_TestPlayer_setName(){
		TestPlayer testPlayer = new TestPlayer(hand, play, name, sortStrategy);
		testPlayer.setName("David");
		String expectResult = "David";
		String actualResult = testPlayer.getName();
		assertEquals(expectResult, actualResult);
	}

}