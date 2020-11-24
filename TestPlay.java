
import static org.junit.Assert.assertEquals;

import org.junit.Test;
public class TestPlay {
	@Test
	public void test_play_01(){
		class Cardstub1 extends Card{
			public int getValue() {
				return 2;
			}
		}
		class Cardstub2 extends Card {
			public int getValue() {
				return 4;
			}
		}
		Play play = new Play();
		Cardstub1 card = new Cardstub1();//3D
		Cardstub2 card2 = new Cardstub2();//3C
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		assertEquals(false, play.isEqual(comb, 0, 1, CardValueType.VALUE));	
		}
	
	@Test
	public void test_play_02(){
		class Cardstub1 extends Card{
			public int getSuit() {
				return 2;
			}
		}
		class Cardstub2 extends Card {
			public int getSuit() {
				return 4;
			}
		}
		Play play = new Play();
		Cardstub1 card = new Cardstub1();//3D
		Cardstub2 card2 = new Cardstub2();//3C
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		assertEquals(false, play.isEqual(comb, 0, 1, CardValueType.SUIT));	
		}
	@Test
	public void test_play_03(){
		Play play = new Play();
		Card card = new Card(1,1);//4C
		Combination comb = new Combination();
		comb.addCard(card);
		assertEquals(ComboType.SINGLE, play.checkType(comb));
	}
	@Test
	public void test_play_04(){
		Play play = new Play();
		Card card = new Card(2,2);//5H
		Card card2 = new Card(2,3);//3S
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		assertEquals(ComboType.PAIR, play.checkType(comb));
	}
	@Test
	public void test_play_05(){
		Play play = new Play();
		Card card = new Card(1,1);//4C
		Card card2 = new Card(1,2);//4H
		Card card3= new Card(1,3);//4S
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		assertEquals(ComboType.THREE_OF_KIND, play.checkType(comb));
	}
	@Test
	public void test_play_06(){
		Play play = new Play();
		Card card = new Card(0,1);//3C
		Card card2 = new Card(1,1);//4C
		Card card3= new Card(2,1);//4C
		Card card4= new Card(11,3);//6S
		Card card5= new Card(12,4);//6S
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.STRAIGHT, play.checkType(comb));
	}
	
	@Test
	public void test_play_07(){
		Play play = new Play();
		Card card = new Card(0,1);
		Card card2 = new Card(1,1);
		Card card3= new Card(2,1);
		Card card4= new Card(11,1);
		Card card5= new Card(12,1);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.STRAIGHT_FLUSH, play.checkType(comb));
	}
	
	@Test
	public void test_play_08(){
		Play play = new Play();
		Card card = new Card(1,0);
		Card card2 = new Card(1,1);
		Card card3= new Card(1,2);
		Card card4= new Card(1,3);
		Card card5= new Card(12,1);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FOUR_PLUS_ONE, play.checkType(comb));
	}
	
	@Test
	public void test_play_09(){
		Play play = new Play();
		Card card = new Card(12,1);
		Card card2 = new Card(1,0);
		Card card3= new Card(1,1);
		Card card4= new Card(1,2);
		Card card5= new Card(1,3);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FOUR_PLUS_ONE, play.checkType(comb));
	}
	
	@Test
	public void test_play_10(){
		Play play = new Play();
		Card card = new Card(1,0);
		Card card2 = new Card(1,1);
		Card card3= new Card(1,2);
		Card card4= new Card(2,2);
		Card card5= new Card(2,3);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FULL_HOUSE, play.checkType(comb));
	}
	
	@Test
	public void test_play_11(){
		Play play = new Play();
		Card card = new Card(1,0);
		Card card2 = new Card(1,1);
		Card card3= new Card(2,1);
		Card card4= new Card(2,2);
		Card card5= new Card(2,3);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FULL_HOUSE, play.checkType(comb));
	}
	@Test
	public void test_play_12(){
		Play play = new Play();
		Card card = new Card(1,1);
		Card card2 = new Card(2,1);
		Card card3= new Card(3,1);
		Card card4= new Card(7,1);
		Card card5= new Card(5,1);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FLUSH, play.checkType(comb));
	}
	
		
	    
	}


