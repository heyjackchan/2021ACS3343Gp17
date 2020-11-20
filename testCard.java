import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testCard {

	//Test case A0101: Create an empty card, get it's index.
	@Test
	public void test_NoCards_getIndex() {
		Card card = new Card();
		int expectResult = -5;
		int actualResult = card.getIndex();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case A0102: Create an empty card, get it's value.
	@Test
	public void test_NoCards_getValue() {
		Card card = new Card();
		int expectResult = -1;
		int actualResult = card.getValue();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case A0103: Create an empty card, get it's suit.
	@Test
	public void test_NoCards_getSuit() {
		Card card = new Card();
		int expectResult = -1;
		int actualResult = card.getSuit();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case A0104: Create an empty card, get it's discard status.
	@Test
	public void test_NoCards_getDiscardStatus() {
		Card card = new Card();
		boolean expectResult = false;
		boolean actualResult = card.isDiscarded();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case A0105: Create card "D3", get it's card symbol.
	@Test
	public void test_CardD3_getCardSymbol() {
		Card card = new Card(0,0);
		String expectResult = "D3";
		String actualResult = card.printCardSymbol();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case A0106: Create card "C10(CX)" as object, set discard as true and get its latest discard status.
	@Test
	public void test_CardCXObj_setDiscarded() {
		Card cardObj = new Card(7,1);
		Card card = new Card(cardObj);
		card.setDiscarded(true);
		boolean expectResult = true;
		boolean actualResult = card.isDiscarded();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case A0107: Create card "S2" as object, print out its information.
	@Test
	public void test_CardS2Obj_toString() {
		Card cardObj = new Card(12,3);
		Card card = new Card(cardObj);
		String expectResult = "Card{index= 51, value= 12, suit= 3, discarded= false, CardSymbol= S2}";
		String actualResult = card.toString();
		assertEquals(expectResult, actualResult);
	}

}
