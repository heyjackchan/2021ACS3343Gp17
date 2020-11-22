import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testDealer {

	//Test case A0401: Create an new deck with all cards are diamond, print out its information.
	@Test
	public void test_NewDeck_shuffle() {
		Dealer dealer = new Dealer();
		System.out.println(dealer.toString());
		dealer.shuffle();
		System.out.println(dealer.toString());
		
		
		String expectResult = "";
		String actualResult = "";
		assertEquals(expectResult, actualResult);
	}
} 
