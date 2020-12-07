package testbig2pokergame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import big2pokergame.Card;
import big2pokergame.CardPile;
import big2pokergame.Combination;

class testCombination {

	// Test case INTE0401: Create a new combination without any value assigned to
	// test toString() function.
	@Test
	public void testCombination_toString_CombinationNull() {
		Combination combination = new Combination();
		String expectResult = "combination{\n" + "cardNo=0, \n" + "}";
		String actualResult = combination.toString();
		assertEquals(expectResult, actualResult);
	}

	// Test case INTE0402: Create a new combination of a pair of 3 to test
	// toString() function.
	@Test
	public void testCombination_toString_CombinationIsPair() {
		class Pair extends CardPile {
			public Pair() {
				super(2);
				super.addCard(new Card(0, 0));
				super.addCard(new Card(0, 1));
			}
		}

		Pair pair = new Pair();
		Combination combination = new Combination(pair);
		String expectResult = "combination{\n" + "cardNo=2, \n"
				+ "Card{index= 0, value= 0, suit= 0, discarded= false, CardSymbol= D3}\n"
				+ "Card{index= 1, value= 0, suit= 1, discarded= false, CardSymbol= C3}\n" + "}";
		String actualResult = combination.toString();
		assertEquals(expectResult, actualResult);
	}

	// Test case INTE0403: Create a new combination with NULL value input to test
	// toString() function.
	@Test
	public void testCombination_toString_CombinationInputNull() {
		Combination combination = new Combination(null);
		String expectResult = "combination{\n" + "cardNo=0, \n" + "}";
		String actualResult = combination.toString();
		assertEquals(expectResult, actualResult);
	}

}
