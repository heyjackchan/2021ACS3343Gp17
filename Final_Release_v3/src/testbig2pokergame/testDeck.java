package testbig2pokergame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import big2pokergame.Card;
import big2pokergame.Deck;

class testDeck {
	// Test case INTE0201: Use the deck to test toString() function.
	@Test
	public void testDeck_toString() {
		Deck deck = new Deck(); // Create a new deck.
		String expectResult = "Deck{\n" + "Card{index= 0, value= 0, suit= 0, discarded= false, CardSymbol= D3}\n"
				+ "Card{index= 5, value= 1, suit= 1, discarded= false, CardSymbol= C4}\n"
				+ "Card{index= 10, value= 2, suit= 2, discarded= false, CardSymbol= H5}\n"
				+ "Card{index= 15, value= 3, suit= 3, discarded= false, CardSymbol= S6}\n"
				+ "Card{index= 16, value= 4, suit= 0, discarded= false, CardSymbol= D7}\n"
				+ "Card{index= 21, value= 5, suit= 1, discarded= false, CardSymbol= C8}\n"
				+ "Card{index= 26, value= 6, suit= 2, discarded= false, CardSymbol= H9}\n"
				+ "Card{index= 31, value= 7, suit= 3, discarded= false, CardSymbol= SX}\n"
				+ "Card{index= 32, value= 8, suit= 0, discarded= false, CardSymbol= DJ}\n"
				+ "Card{index= 37, value= 9, suit= 1, discarded= false, CardSymbol= CQ}\n"
				+ "Card{index= 42, value= 10, suit= 2, discarded= false, CardSymbol= HK}\n"
				+ "Card{index= 47, value= 11, suit= 3, discarded= false, CardSymbol= SA}\n"
				+ "Card{index= 48, value= 12, suit= 0, discarded= false, CardSymbol= D2}\n"
				+ "Card{index= 1, value= 0, suit= 1, discarded= false, CardSymbol= C3}\n"
				+ "Card{index= 6, value= 1, suit= 2, discarded= false, CardSymbol= H4}\n"
				+ "Card{index= 11, value= 2, suit= 3, discarded= false, CardSymbol= S5}\n"
				+ "Card{index= 12, value= 3, suit= 0, discarded= false, CardSymbol= D6}\n"
				+ "Card{index= 17, value= 4, suit= 1, discarded= false, CardSymbol= C7}\n"
				+ "Card{index= 22, value= 5, suit= 2, discarded= false, CardSymbol= H8}\n"
				+ "Card{index= 27, value= 6, suit= 3, discarded= false, CardSymbol= S9}\n"
				+ "Card{index= 28, value= 7, suit= 0, discarded= false, CardSymbol= DX}\n"
				+ "Card{index= 33, value= 8, suit= 1, discarded= false, CardSymbol= CJ}\n"
				+ "Card{index= 38, value= 9, suit= 2, discarded= false, CardSymbol= HQ}\n"
				+ "Card{index= 43, value= 10, suit= 3, discarded= false, CardSymbol= SK}\n"
				+ "Card{index= 44, value= 11, suit= 0, discarded= false, CardSymbol= DA}\n"
				+ "Card{index= 49, value= 12, suit= 1, discarded= false, CardSymbol= C2}\n"
				+ "Card{index= 2, value= 0, suit= 2, discarded= false, CardSymbol= H3}\n"
				+ "Card{index= 7, value= 1, suit= 3, discarded= false, CardSymbol= S4}\n"
				+ "Card{index= 8, value= 2, suit= 0, discarded= false, CardSymbol= D5}\n"
				+ "Card{index= 13, value= 3, suit= 1, discarded= false, CardSymbol= C6}\n"
				+ "Card{index= 18, value= 4, suit= 2, discarded= false, CardSymbol= H7}\n"
				+ "Card{index= 23, value= 5, suit= 3, discarded= false, CardSymbol= S8}\n"
				+ "Card{index= 24, value= 6, suit= 0, discarded= false, CardSymbol= D9}\n"
				+ "Card{index= 29, value= 7, suit= 1, discarded= false, CardSymbol= CX}\n"
				+ "Card{index= 34, value= 8, suit= 2, discarded= false, CardSymbol= HJ}\n"
				+ "Card{index= 39, value= 9, suit= 3, discarded= false, CardSymbol= SQ}\n"
				+ "Card{index= 40, value= 10, suit= 0, discarded= false, CardSymbol= DK}\n"
				+ "Card{index= 45, value= 11, suit= 1, discarded= false, CardSymbol= CA}\n"
				+ "Card{index= 50, value= 12, suit= 2, discarded= false, CardSymbol= H2}\n"
				+ "Card{index= 3, value= 0, suit= 3, discarded= false, CardSymbol= S3}\n"
				+ "Card{index= 4, value= 1, suit= 0, discarded= false, CardSymbol= D4}\n"
				+ "Card{index= 9, value= 2, suit= 1, discarded= false, CardSymbol= C5}\n"
				+ "Card{index= 14, value= 3, suit= 2, discarded= false, CardSymbol= H6}\n"
				+ "Card{index= 19, value= 4, suit= 3, discarded= false, CardSymbol= S7}\n"
				+ "Card{index= 20, value= 5, suit= 0, discarded= false, CardSymbol= D8}\n"
				+ "Card{index= 25, value= 6, suit= 1, discarded= false, CardSymbol= C9}\n"
				+ "Card{index= 30, value= 7, suit= 2, discarded= false, CardSymbol= HX}\n"
				+ "Card{index= 35, value= 8, suit= 3, discarded= false, CardSymbol= SJ}\n"
				+ "Card{index= 36, value= 9, suit= 0, discarded= false, CardSymbol= DQ}\n"
				+ "Card{index= 41, value= 10, suit= 1, discarded= false, CardSymbol= CK}\n"
				+ "Card{index= 46, value= 11, suit= 2, discarded= false, CardSymbol= HA}\n"
				+ "Card{index= 51, value= 12, suit= 3, discarded= false, CardSymbol= S2}\n" + "}";
		String actualResult = deck.toString();
		assertEquals(expectResult, actualResult);
	}

	// Test case INTE0202: Use the deck to test getCapacity() function.
	@Test
	public void testDeck_getCapacity() {
		Deck deck = new Deck(); // Create a new deck.
		int expectResult = 52;
		int actualResult = deck.getCapacity();
		assertEquals(expectResult, actualResult);
	}

	// Test case INTE0203: Use the deck to test getCardByIndex() function by getting
	// index=1's information.
	@Test
	public void testDeck_getCardByIndex() {
		Deck deck = new Deck(); // Create a new deck.
		String expectResult = "Card{index= 5, value= 1, suit= 1, discarded= false, CardSymbol= C4}";
		String actualResult = deck.getCardByIndex(1).toString();
		assertEquals(expectResult, actualResult);
	}

	// Test case INTE0204: Use the deck to test getCardNo() function by getting
	// index=1's information.
	@Test
	public void testDeck_getCardNo() {
		Deck deck = new Deck(); // Create a new deck.
		int expectResult = 0;
		int actualResult = deck.getCardNo();
		assertEquals(expectResult, actualResult);
	}

	// Test case INTE0205: Use the deck to test setCardByIndex() function.
	@Test
	public void testDeck_setCardByIndex() {
		Deck deck = new Deck(); // Create a new deck.
		Card card = new Card(1, 1); // Which is Club-4.
		deck.setCardByIndex(card, 5);
		String expectResult = "Card{index= 5, value= 1, suit= 1, discarded= false, CardSymbol= C4}";
		String actualResult = deck.getCardByIndex(5).toString();
		assertEquals(expectResult, actualResult);
	}

}
