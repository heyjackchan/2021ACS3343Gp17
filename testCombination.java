import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testCombination {

	//Test case UNIT0701: Create a new combination without any value assigned to test toString() function.
	@Test
	public void testCombination_CombinationNull_toString() {
		Combination combination = new Combination();
		String expectResult = "combination{\n"
				+ "cardNo=0, \n"
				+ "}";
		String actualResult = combination.toString();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case UNIT0702: Create a new combination of a pair of 3 to test toString() function.
	@Test
	public void testCombination_CombinationCardObj_withPair_toString() {
		class Pair extends CardPile {
			public Pair() {
				super(2);
				super.addCard(new Card(0,0));
				super.addCard(new Card(0,1));
			}
		}
		
		Pair pair = new Pair();
		Combination combination = new Combination(pair);
		String expectResult = "combination{\n"
				+ "cardNo=2, \n"
				+ "Card{index= 0, value= 0, suit= 0, discarded= false, CardSymbol= D3}\n"
				+ "Card{index= 1, value= 0, suit= 1, discarded= false, CardSymbol= C3}\n"
				+ "}";
		String actualResult = combination.toString();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case UNIT0703: Create a new combination with NULL value inputed to test toString() function.
	@Test
	public void testCombination_CombinationInputNull_toString() {
		Combination combination = new Combination(null);
		System.out.println(combination.toString());
		String expectResult = "combination{\n"
				+ "cardNo=0, \n"
				+ "}";
		String actualResult = combination.toString();
		assertEquals(expectResult, actualResult);
	}
	
} 
