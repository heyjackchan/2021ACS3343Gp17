import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testCombination {

	//Test case A0701: Create an new deck with all cards are diamond, print out its information.
	@Test
	public void test_NewCombination_Null_toString() {
		Combination combination = new Combination();
		String expectResult = "combination{\n"
				+ "cardNo=0, \n"
				+ "}";
		String actualResult = combination.toString();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case A0702: Create an new deck with all cards are diamond, print out its information.
	@Test
	public void test_NewCombination_CardObj_withPair_toString() {
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
	
	//Test case A0704: Create an new deck with all cards are diamond, print out its information.
	@Test
	public void test_NewCombination_inputNull_toString() {
		Combination combination = new Combination(null);
		System.out.println(combination.toString());
		String expectResult = "combination{\n"
				+ "cardNo=0, \n"
				+ "}";
		String actualResult = combination.toString();
		assertEquals(expectResult, actualResult);
	}
	
} 
