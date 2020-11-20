import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testHand {

	//Test case A0301: Create an new deck, print out its information.
	@Test
	public void test_NewHand_toString() {
		Hand hand = new Hand();
		System.out.print(hand.toString());
		String expectResult = "";
		String actualResult = "";
		assertEquals(expectResult, actualResult);
	}
	
} 
