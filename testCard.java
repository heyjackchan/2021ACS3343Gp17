import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testCard {
	Card card = new Card(); //This is a card without any value assigned.
	Card cardD3 = new Card(0,0); //This is the card Diamond-3.
	Card cardCX = new Card(7,1); //This is the card Club-10.
	Card cardS2 = new Card(12,3); //This is the card Spade-2.
	
	//Test case UNIT0101: Use a card without any value assigned to test getIndex() function.
	@Test
	public void testCard_NoValueCard_getIndex() {
		int expectResult = -5;
		int actualResult = card.getIndex();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case UNIT0102: Use a card without any value assigned to test getValue() function.
	@Test
	public void testCard_NoValueCard_getValue() {
		int expectResult = -1;
		int actualResult = card.getValue();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case UNIT0103: Use a card without any value assigned to test getSuit() function.
	@Test
	public void testCard_NoValueCard_getSuit() {
		int expectResult = -1;
		int actualResult = card.getSuit();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case UNIT0104: Use a card without any value assigned to test isDiscarded() function.
	@Test
	public void testCard_NoValueCard_getDiscardStatus() {
		boolean expectResult = false;
		boolean actualResult = card.isDiscarded();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case UNIT0105: Use the card Diamond-3 to test printCardSymbol() function.
	@Test
	public void testCard_CardD3_getCardSymbol() {
		String expectResult = "D3";
		String actualResult = cardD3.printCardSymbol();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case UNIT0106: 
	//Use the card Club-10 to create Card object, set its discard status as true,
	//to test setDiscarded(boolean discarded) function.
	@Test
	public void testCard_CardCX_asObj_setDiscarded() {
		Card cardObj = new Card(cardCX);
		cardObj.setDiscarded(true);
		boolean expectResult = true;
		boolean actualResult = cardObj.isDiscarded();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case UNIT0107: 
	//Use the card Spade-2 to create Card object, set its discard status as true,
	//to test toString() function.
	@Test
	public void testCard_CardS2_asObj_toString() {
		Card cardObj = new Card(cardS2);
		String expectResult = "Card{index= 51, value= 12, suit= 3, discarded= false, CardSymbol= S2}";
		String actualResult = cardObj.toString();
		assertEquals(expectResult, actualResult);
	}

}
