import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testCardPile {
	private class CardPile_All3 extends CardPile{
		public CardPile_All3() {
			super(4);
			super.addCard(new Card(0,0));
			super.addCard(new Card(0,1));
			super.addCard(new Card(0,2));
			super.addCard(new Card(0,3));
			
		}
		public CardPile_All3(CardPile cards) {
			super(cards);
		}
		
	}
	
	private class CardPile_Null extends CardPile{
		public CardPile_Null() {
			super(null);
		}
		public CardPile_Null(CardPile cards) {
			super(cards);
		}
	}

	//Test case A0201: Create an new card combination which is the 4 card of value 3, test CardPile's toString output function.
	@Test
	public void test_NewCardCombinationType_All3_cardPileToString() {
		CardPile_All3 cardPile_All3 = new CardPile_All3();
		String expectResult = "combination{\n"
				+ "cardNo=4, \n"
				+ "Card{index= 0, value= 0, suit= 0, discarded= false, CardSymbol= D3}\n"
				+ "Card{index= 1, value= 0, suit= 1, discarded= false, CardSymbol= C3}\n"
				+ "Card{index= 2, value= 0, suit= 2, discarded= false, CardSymbol= H3}\n"
				+ "Card{index= 3, value= 0, suit= 3, discarded= false, CardSymbol= S3}\n"
				+ "}";
		String actualResult = cardPile_All3.toString();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case A0202: Create an new card combination which is the 4 card of value 3 as an Card object, test CardPile's toString output function.
	@Test
	public void test_NewCardCombinationType_All3_asObject() {
		CardPile_All3 cardPile_All3 = new CardPile_All3();
		CardPile_All3 cardPile_All3Obj = new CardPile_All3(cardPile_All3);
		String expectResult = "combination{\n"
				+ "cardNo=4, \n"
				+ "Card{index= 0, value= 0, suit= 0, discarded= false, CardSymbol= D3}\n"
				+ "Card{index= 1, value= 0, suit= 1, discarded= false, CardSymbol= C3}\n"
				+ "Card{index= 2, value= 0, suit= 2, discarded= false, CardSymbol= H3}\n"
				+ "Card{index= 3, value= 0, suit= 3, discarded= false, CardSymbol= S3}\n"
				+ "}";
		String actualResult = cardPile_All3Obj.toString();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case A0203: Create an new card combination which does not contain any card, test CardPile's toString output function.
	@Test
	public void test_NewCardCombinationType_Null_cardPileToString() {
		CardPile_Null cardPile_Null = new CardPile_Null();
		String expectResult = "combination{\n"
				+ "cardNo=0, \n"
				+ "}";
		String actualResult = cardPile_Null.toString();
		assertEquals(expectResult, actualResult);
	}

	//Test case A0204: Create an new card combination which does not contain any card as an Card object, test CardPile's toString output function.
	@Test
	public void test_NewCardCombinationType_Null_asObject() {
		CardPile_Null cardPile_Null = new CardPile_Null();
		CardPile_Null cardPile_NullObj = new CardPile_Null(cardPile_Null);
		String expectResult = "combination{\n"
				+ "cardNo=0, \n"
				+ "}";
		String actualResult = cardPile_NullObj.toString();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case A0205: Create an new card combination which is the 4 card of value 3, test CardPile's add card function with exceeded data.
	@Test
	public void test_NewCardCombinationType_All3_addCardError() {
		class CardPile_All3Exceed extends CardPile{
			public CardPile_All3Exceed() {
				super(4);
				super.addCard(new Card(0,0));
				super.addCard(new Card(0,1));
				super.addCard(new Card(0,2));
				super.addCard(new Card(0,3));
				super.addCard(new Card(1,1));
			}
		}
		CardPile_All3Exceed cardPile_All3Exceed = new CardPile_All3Exceed();
		String expectResult = "combination{\n"
				+ "cardNo=4, \n"
				+ "Card{index= 0, value= 0, suit= 0, discarded= false, CardSymbol= D3}\n"
				+ "Card{index= 1, value= 0, suit= 1, discarded= false, CardSymbol= C3}\n"
				+ "Card{index= 2, value= 0, suit= 2, discarded= false, CardSymbol= H3}\n"
				+ "Card{index= 3, value= 0, suit= 3, discarded= false, CardSymbol= S3}\n"
				+ "}";
		String actualResult = cardPile_All3Exceed.toString();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case A0201: Create an new card combination which is the 4 card of value 3, test CardPile's clear function by return card number as 0.
	@Test
	public void test_NewCardCombinationType_All3_clear() {
		CardPile_All3 cardPile_All3 = new CardPile_All3();
		cardPile_All3.clear();
		int expectResult = 0;
		int actualResult = cardPile_All3.getCardNo();
		assertEquals(expectResult, actualResult);
	}


}
