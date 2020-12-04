import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testCardPile {
	//Test case UNIT0201: Use cardPile type CardPileNone to test toString() function.
	@Test
	public void testCardPile_CardPileNone_toString() {
		class CardPileNone extends CardPile{
			//This function creates a cardPile type without any value assigned.
			public CardPileNone() {
				super(null);
			}
		}
		CardPileNone cardPileNull = new CardPileNone(); //This is the cardPile type cardPileNull.
		String expectResult = "combination{\n"
				+ "cardNo=0, \n"
				+ "}";
		String actualResult = cardPileNull.toString();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case UNIT0202: Use cardPile type CardPileNone to create CardPile object to test toString() function.
	@Test
	public void testCardPile_CardPileNone_asObj_toString() {
		class CardPileNone extends CardPile{
			//This function creates a cardPile type without any value assigned.
			public CardPileNone() {
				super(null);
			}
			public CardPileNone(CardPile cards) {
				super(cards);
			}
		}
		CardPileNone cardPileNull = new CardPileNone(); //This is the cardPile type cardPileNull.
		CardPileNone cardPileNullObj = new CardPileNone(cardPileNull);
		String expectResult = "combination{\n"
				+ "cardNo=0, \n"
				+ "}";
		String actualResult = cardPileNullObj.toString();
		assertEquals(expectResult, actualResult);
	}
	
	
	
	
	
	
	
	
	
	
	
	//Test case INTE0201: Use cardPile type CardPileAll3 to test toString() function.
	@Test
	public void testCardPile_CardPileAll3_toString() {
		class CardPileAll3 extends CardPile{
			//This function creates a cardPile type which contains all four cards of 3 (i.e. D3, C3, H3, S3).
			public CardPileAll3() {
				super(4);
		        cards = new Card[capacity];
		        super.addCard(new Card(0, 0));
		        super.addCard(new Card(0, 1));
		        super.addCard(new Card(0, 2));
		        super.addCard(new Card(0, 3));
				
			}
		}
		CardPileAll3 cardPileAll3 = new CardPileAll3(); //This is the cardPile type cardPileAll3.
		String expectResult = "combination{\n"
				+ "cardNo=4, \n"
				+ "Card{index= 0, value= 0, suit= 0, discarded= false, CardSymbol= D3}\n"
				+ "Card{index= 1, value= 0, suit= 1, discarded= false, CardSymbol= C3}\n"
				+ "Card{index= 2, value= 0, suit= 2, discarded= false, CardSymbol= H3}\n"
				+ "Card{index= 3, value= 0, suit= 3, discarded= false, CardSymbol= S3}\n"
				+ "}";
		String actualResult = cardPileAll3.toString();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case INTE0202: Use cardPile type CardPileAll3 to create CardPile object to test toString() function.
	@Test
	public void testCardPile_CardPileAll3_asObj_toString() {
		class CardPileAll3 extends CardPile{
			//This function creates a cardPile type which contains all four cards of 3 (i.e. D3, C3, H3, S3).
			public CardPileAll3() {
				super(4);
		        cards = new Card[capacity];
		        super.addCard(new Card(0, 0));
		        super.addCard(new Card(0, 1));
		        super.addCard(new Card(0, 2));
		        super.addCard(new Card(0, 3));
				
			}
			public CardPileAll3(CardPile cards) {
				super(cards);
			}
		}
		CardPileAll3 cardPileAll3 = new CardPileAll3(); //This is the cardPile type cardPileAll3.
		CardPileAll3 cardPileAll3Obj = new CardPileAll3(cardPileAll3);
		String expectResult = "combination{\n"
				+ "cardNo=4, \n"
				+ "Card{index= 0, value= 0, suit= 0, discarded= false, CardSymbol= D3}\n"
				+ "Card{index= 1, value= 0, suit= 1, discarded= false, CardSymbol= C3}\n"
				+ "Card{index= 2, value= 0, suit= 2, discarded= false, CardSymbol= H3}\n"
				+ "Card{index= 3, value= 0, suit= 3, discarded= false, CardSymbol= S3}\n"
				+ "}";
		String actualResult = cardPileAll3Obj.toString();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case INTE0203: Use cardPile type CardPileAll3 to test addCard(Card card) function with exceeded data.
	@Test
	public void testCardPile_CardPileAll3_addCardERROR() {
		class CardPileAll3Exceed extends CardPile{
			public CardPileAll3Exceed() {
				super(4);
				super.addCard(new Card(0,0));
				super.addCard(new Card(0,1));
				super.addCard(new Card(0,2));
				super.addCard(new Card(0,3));
				super.addCard(new Card(1,1));
			}
		}
		CardPileAll3Exceed cardPileAll3Exceed = new CardPileAll3Exceed();
		String expectResult = "combination{\n"
				+ "cardNo=4, \n"
				+ "Card{index= 0, value= 0, suit= 0, discarded= false, CardSymbol= D3}\n"
				+ "Card{index= 1, value= 0, suit= 1, discarded= false, CardSymbol= C3}\n"
				+ "Card{index= 2, value= 0, suit= 2, discarded= false, CardSymbol= H3}\n"
				+ "Card{index= 3, value= 0, suit= 3, discarded= false, CardSymbol= S3}\n"
				+ "}";
		String actualResult = cardPileAll3Exceed.toString();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case INTE0204: Use cardPile type CardPileAll3 to test clear() function.
	@Test
	public void testCardPile_CardPileAll3_clear() {
		class CardPileAll3 extends CardPile{
			//This function creates a cardPile type which contains all four cards of 3 (i.e. D3, C3, H3, S3).
			public CardPileAll3() {
				super(4);
		        cards = new Card[capacity];
		        super.addCard(new Card(0, 0));
		        super.addCard(new Card(0, 1));
		        super.addCard(new Card(0, 2));
		        super.addCard(new Card(0, 3));
				
			}
		}
		CardPileAll3 cardPileAll3 = new CardPileAll3();
		cardPileAll3.clear();
		int expectResult = 0;
		int actualResult = cardPileAll3.getCardNo();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case INTE0205: Use cardPile type CardPileAll3 to test getCapacity() function.
	@Test
	public void testCardPile_CardPileAll3_getCapacity() {
		class CardPileAll3 extends CardPile{
			//This function creates a cardPile type which contains all four cards of 3 (i.e. D3, C3, H3, S3).
			public CardPileAll3() {
				super(4);
		        cards = new Card[capacity];
		        super.addCard(new Card(0, 0));
		        super.addCard(new Card(0, 1));
		        super.addCard(new Card(0, 2));
		        super.addCard(new Card(0, 3));
				
			}
		}
		CardPileAll3 cardPileAll3 = new CardPileAll3();
		int expectResult = 4;
		int actualResult = cardPileAll3.getCapacity();
		assertEquals(expectResult, actualResult);
	}
	
	
	//Test case INTE0206: Use cardPile type CardPileAll3 to test setCardByIndex() function.
	@Test
	public void testCardPile_CardPileAll3_setCardByIndex() {
		class CardPileAll3 extends CardPile{
			//This function creates a cardPile type which contains all four cards of 3 (i.e. D3, C3, H3, S3).
			public CardPileAll3() {
				super(4);
		        cards = new Card[capacity];
		        super.addCard(new Card(0, 0));
		        super.addCard(new Card(0, 1));
		        super.addCard(new Card(0, 2));
		        super.addCard(new Card(0, 3));
				
			}
		}
		CardPileAll3 cardPileAll3 = new CardPileAll3();
		cardPileAll3.setCardByIndex(new Card(1, 0), 1);
		String expectResult = "combination{\n"
				+ "cardNo=4, \n"
				+ "Card{index= 0, value= 0, suit= 0, discarded= false, CardSymbol= D3}\n"
				+ "Card{index= 4, value= 1, suit= 0, discarded= false, CardSymbol= D4}\n"
				+ "Card{index= 2, value= 0, suit= 2, discarded= false, CardSymbol= H3}\n"
				+ "Card{index= 3, value= 0, suit= 3, discarded= false, CardSymbol= S3}\n"
				+ "}";
			String actualResult = cardPileAll3.toString();
			assertEquals(expectResult, actualResult);
		}
}
