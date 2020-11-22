import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testHand {

	//Test case A0401: Create an new deck with all cards are diamond, print out its information.
	@Test
	public void test_NewHand_toString() {
		Hand hand = new Hand();
		for(int i=0;i<13;i++) {
			hand.addCard(new Card(i,0));
		}
		String expectResult = "\n"
				+ "Card{index= 0, value= 0, suit= 0, discarded= false, CardSymbol= D3}\n"
				+ "Card{index= 4, value= 1, suit= 0, discarded= false, CardSymbol= D4}\n"
				+ "Card{index= 8, value= 2, suit= 0, discarded= false, CardSymbol= D5}\n"
				+ "Card{index= 12, value= 3, suit= 0, discarded= false, CardSymbol= D6}\n"
				+ "Card{index= 16, value= 4, suit= 0, discarded= false, CardSymbol= D7}\n"
				+ "Card{index= 20, value= 5, suit= 0, discarded= false, CardSymbol= D8}\n"
				+ "Card{index= 24, value= 6, suit= 0, discarded= false, CardSymbol= D9}\n"
				+ "Card{index= 28, value= 7, suit= 0, discarded= false, CardSymbol= DX}\n"
				+ "Card{index= 32, value= 8, suit= 0, discarded= false, CardSymbol= DJ}\n"
				+ "Card{index= 36, value= 9, suit= 0, discarded= false, CardSymbol= DQ}\n"
				+ "Card{index= 40, value= 10, suit= 0, discarded= false, CardSymbol= DK}\n"
				+ "Card{index= 44, value= 11, suit= 0, discarded= false, CardSymbol= DA}\n"
				+ "Card{index= 48, value= 12, suit= 0, discarded= false, CardSymbol= D2}\n"
				+ "";
		String actualResult = hand.toString();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case A0402: Create an new deck with all cards are diamond, release first card, print its latest information.
	@Test
	public void test_NewHand_releaseCard() {
		Hand hand = new Hand();
		for(int i=0;i<13;i++) {
			hand.addCard(new Card(i,0));
		}
		hand.releaseCard(0);
		String expectResult = "\n"
				+ "Card{index= 0, value= 0, suit= 0, discarded= true, CardSymbol= D3}\n"
				+ "Card{index= 4, value= 1, suit= 0, discarded= false, CardSymbol= D4}\n"
				+ "Card{index= 8, value= 2, suit= 0, discarded= false, CardSymbol= D5}\n"
				+ "Card{index= 12, value= 3, suit= 0, discarded= false, CardSymbol= D6}\n"
				+ "Card{index= 16, value= 4, suit= 0, discarded= false, CardSymbol= D7}\n"
				+ "Card{index= 20, value= 5, suit= 0, discarded= false, CardSymbol= D8}\n"
				+ "Card{index= 24, value= 6, suit= 0, discarded= false, CardSymbol= D9}\n"
				+ "Card{index= 28, value= 7, suit= 0, discarded= false, CardSymbol= DX}\n"
				+ "Card{index= 32, value= 8, suit= 0, discarded= false, CardSymbol= DJ}\n"
				+ "Card{index= 36, value= 9, suit= 0, discarded= false, CardSymbol= DQ}\n"
				+ "Card{index= 40, value= 10, suit= 0, discarded= false, CardSymbol= DK}\n"
				+ "Card{index= 44, value= 11, suit= 0, discarded= false, CardSymbol= DA}\n"
				+ "Card{index= 48, value= 12, suit= 0, discarded= false, CardSymbol= D2}\n"
				+ "";
		String actualResult = hand.toString();
		assertEquals(expectResult, actualResult);
	}
} 
