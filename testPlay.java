import static org.junit.Assert.*;

import org.junit.Test;

public class testPlay {

	//Testing for sort()
	@Test
	public void testPlay_Play_sort() {
		Play testSort = new Play();
		Combination testSortCombination = new Combination();
		testSortCombination.addCard(new Card(7,1));
		testSortCombination.addCard(new Card(8,0));
		testSortCombination.addCard(new Card(4,3));
		testSortCombination.addCard(new Card(4,1));
		testSortCombination.addCard(new Card(10,2));
		testSort.currentCombination = testSortCombination;
		testSort.sort();
		String expectedSort =  "\ncurrentCombination={ \n" 
								+"combination{\n"
								+ "cardNo=5, \n"
								+ "Card{index= 17, value= 4, suit= 1, discarded= false, CardSymbol= C7}\n"
								+ "Card{index= 19, value= 4, suit= 3, discarded= false, CardSymbol= S7}\n"
								+ "Card{index= 29, value= 7, suit= 1, discarded= false, CardSymbol= CX}\n"
								+ "Card{index= 32, value= 8, suit= 0, discarded= false, CardSymbol= DJ}\n"
								+ "Card{index= 42, value= 10, suit= 2, discarded= false, CardSymbol= HK}\n"
								+ "}, \n}\n"
								+ "previousCombination={ \n" 
								+ "combination{\n"
								+ "cardNo=0, \n}, \n}\n";
		String resultSort = testSort.toString();
		assertEquals(expectedSort, resultSort);
	}
	
	//Testing for getCurrentCombination()
	@Test
	public void testPlay_Play_testGetCurrentCombination() {
		Combination testGetCombination1 = new Combination();
		testGetCombination1.addCard(new Card(1,0));
		testGetCombination1.addCard(new Card(2,1));
		
		Play testGetCur = new Play();
		testGetCur.currentCombination = testGetCombination1;
		
		Combination expectedCombination1 = testGetCur.getCurrentCombination();
		assertEquals(expectedCombination1, testGetCombination1);
		
	}
	
	//Testing for getPreviousCombination()
	@Test
	public void testPlay_Play_testGetPreviousCombination() {
		Combination testGetCombination2 = new Combination();
		testGetCombination2.addCard(new Card(5,2));
		testGetCombination2.addCard(new Card(9,3));
		
		Play testGetPre = new Play();
		testGetPre.previousCombination = testGetCombination2;
		
		Combination expectedCombination2 = testGetPre.getPreviousCombination();
		assertEquals(expectedCombination2, testGetCombination2);
	}
	
	//Test stub for Play
	class testPlayStub extends Play{
		public testPlayStub() {
			super();
		}
		public boolean isEqual(Combination combo, int from, int to, CardValueType type) {
	        boolean equal = true;
	        if (type.equals(CardValueType.VALUE)) {
	            for (int i = from; i <= to; i++) {
	                if (combo.getCardByIndex(from).getValue() != combo.getCardByIndex(i).getValue()) {
	                    equal = false;
	                    break;
	                }
	            }
	        }
	        if (type.equals(CardValueType.SUIT)) {
	            for (int i = from; i <= to; i++) {
	                if (combo.getCardByIndex(from).getSuit() != combo.getCardByIndex(i).getSuit()) {
	                    equal = false;
	                    break;
	                }
	            }
	        }
	        return equal;
	    }
		
		public ComboType checkType(Combination combo) {
	        ComboType type = ComboType.NONE;
	        switch (combo.getCardNo()) {
	            case 1:
	                type = ComboType.SINGLE; //a card
	                break;
	            case 2:
	                if (isEqual(combo, 0, 1,  CardValueType.VALUE))
	                    type = ComboType.PAIR; //pair
	                break;
	            case 3:
	                if (isEqual(combo, 0, 2,  CardValueType.VALUE))
	                    type = ComboType.THREE_OF_KIND; // three card
	                break;
	            case 5:
	                boolean straight = true;
	                boolean flush = isEqual(combo, 0, 4, CardValueType.SUIT);

	                for (int i = 0; i <= 3; i++) {
	                    if (combo.getCardByIndex(i + 1).getValue() - combo.getCardByIndex(i).getValue() != 1
	                            && !(combo.getCardByIndex(0).getValue() == 0 && combo.getCardByIndex(1).getValue() == 9 && combo.getCardByIndex(2).getValue() == 10 && combo.getCardByIndex(3).getValue() == 11 && combo.getCardByIndex(4).getValue() == 12
	                            || combo.getCardByIndex(0).getValue() == 0 && combo.getCardByIndex(1).getValue() == 1 && combo.getCardByIndex(2).getValue() == 10 && combo.getCardByIndex(3).getValue() == 11 && combo.getCardByIndex(4).getValue() == 12
	                            || combo.getCardByIndex(0).getValue() == 0 && combo.getCardByIndex(1).getValue() == 1 && combo.getCardByIndex(2).getValue() == 2 && combo.getCardByIndex(3).getValue() == 11 && combo.getCardByIndex(4).getValue() == 12
	                            || combo.getCardByIndex(0).getValue() == 0 && combo.getCardByIndex(1).getValue() == 1 && combo.getCardByIndex(2).getValue() == 2 && combo.getCardByIndex(3).getValue() == 3 && combo.getCardByIndex(4).getValue() == 12))
	                    straight = false;
	                }


	                if (straight && flush)
	                    type = ComboType.STRAIGHT_FLUSH; // straight flush
	                else if (isEqual(combo, 0, 3,  CardValueType.VALUE))
	                    type = ComboType.FOUR_PLUS_ONE;  //four plus one
	                else if (isEqual(combo, 1, 4,  CardValueType.VALUE)) {
	                    type = ComboType.FOUR_PLUS_ONE; //four plus one

	                    Card temp = combo.getCardByIndex(0);
	                    for (int i = 0; i < 4; i++) {
	                        combo.setCardByIndex(combo.getCardByIndex(i + 1), i);
	                    }
	                    combo.setCardByIndex(temp, 4);
	                    temp = null;
	                }
	                else if (isEqual(combo, 0, 2, CardValueType.VALUE) && isEqual(combo, 3, 4,  CardValueType.VALUE))
	                    type = ComboType.FULL_HOUSE; // three plus one
	                else if (isEqual(combo, 0, 1, CardValueType.VALUE) && isEqual(combo, 2, 4,  CardValueType.VALUE)) {
	                    type = ComboType.FULL_HOUSE; // three plus one

	                    Card temp = combo.getCardByIndex(0);
	                    Card temp2 = combo.getCardByIndex(1);
	                    for (int i = 0; i < 3; i++) {
	                        combo.setCardByIndex(combo.getCardByIndex(i + 2), i);
	                    }
	                    combo.setCardByIndex(temp, 3);
	                    combo.setCardByIndex(temp2, 4);
	                    temp = null;
	                    temp2 = null;
	                }
	                else if (flush)
	                    type = ComboType.FLUSH; // flush
	                else if (straight)
	                    type = ComboType.STRAIGHT; // straight
	                break;

	        }
	        return type;
	    }
		
	    public boolean compare(Combination previousCombination, Combination currentCombination, int numberOfCard) {
	        if (currentCombination.getCardByIndex(numberOfCard).getValue() > previousCombination.getCardByIndex(numberOfCard).getValue()) {
	            return true;
	        } else if (currentCombination.getCardByIndex(numberOfCard).getValue() == previousCombination.getCardByIndex(numberOfCard).getValue()) {
	            if (currentCombination.getCardByIndex(numberOfCard).getSuit() > previousCombination.getCardByIndex(numberOfCard).getSuit()) {
	                return true;
	            } else {
	                return false;
	            }
	        } else {
	            return false;
	        }
	    }
	}
	
	//Testing for isEqual()
	@Test
	public void testIsEqualWhenInputIsTwoDifferentValue(){
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
		
		testPlayStub play = new testPlayStub();
		Cardstub1 card = new Cardstub1();//3D
		Cardstub2 card2 = new Cardstub2();//3C
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		assertEquals(false, play.isEqual(comb, 0, 1, CardValueType.VALUE));	
		}
	
	@Test
	public void testIsEqualWhenInputIsTwoDifferentSuit(){
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
		testPlayStub play = new testPlayStub();
		Cardstub1 card = new Cardstub1();//3D
		Cardstub2 card2 = new Cardstub2();//3C
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		assertEquals(false, play.isEqual(comb, 0, 1, CardValueType.SUIT));	
		}
	
	//Testing for checkType()
	@Test
	public void testCheckTypeWhenInputIsSingleCard(){
		testPlayStub play = new testPlayStub();
		Card card = new Card(1,1);//4C
		Combination comb = new Combination();
		comb.addCard(card);
		assertEquals(ComboType.SINGLE, play.checkType(comb));
	}
	
	@Test
	public void testCheckTypeWhenInputIsPair(){
		testPlayStub play = new testPlayStub();
		Card card = new Card(2,2);//5H
		Card card2 = new Card(2,3);//3S
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		assertEquals(ComboType.PAIR, play.checkType(comb));
	}
	
	@Test
	public void testCheckTypeWhenInputIsThreeOfKind(){
		testPlayStub play = new testPlayStub();
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
	public void testCheckTypeWhenInputIsStraight(){
		testPlayStub play = new testPlayStub();
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
	public void testCheckTypeWhenInputIsStraightFlush(){
		testPlayStub play = new testPlayStub();
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
	public void testCheckTypeWhenInputIsFourPlusOne(){
		testPlayStub play = new testPlayStub();
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
	public void testCheckTypeWhenInputIsFourPlusOneByDifferentOrder(){
		testPlayStub play = new testPlayStub();
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
	public void testCheckTypeWhenInputIsFullHouse(){
		testPlayStub play = new testPlayStub();
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
	public void testCheckTypeWhenInputIsFullHouseByDifferentOrder(){
		testPlayStub play = new testPlayStub();
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
	public void testCheckTypeWhenInputIsFlush(){
		testPlayStub play = new testPlayStub();
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
	
	//Testing for compare()
	@Test
	public void testPlay_Play_testCompare() {
		Combination testPre = new Combination();
		testPre.addCard(new Card(3,1));
		testPre.addCard(new Card(5,2));
		testPre.addCard(new Card(6,2));
		testPre.addCard(new Card(7,3));
				

		Combination testCur = new Combination();
		testCur.addCard(new Card(4,2));
		testCur.addCard(new Card(5,3));
		testCur.addCard(new Card(6,1));
		testCur.addCard(new Card(0,0));
		
		testPlayStub testCompare = new testPlayStub();

		//Test branch 1
		boolean resultCompare1 = testCompare.compare(testPre, testCur, 0);
		assertEquals(true, resultCompare1);
		
		//Test branch 2
		boolean resultCompare2 = testCompare.compare(testPre, testCur, 1);
		assertEquals(true, resultCompare2);
		
		//Test branch 3
		boolean resultCompare3 = testCompare.compare(testPre, testCur, 2);
		assertEquals(false, resultCompare3);
		
		//Test branch 4
		boolean resultCompare4 = testCompare.compare(testPre, testCur, 3);
		assertEquals(false, resultCompare4);
	}
	
	//Testing for isValidPlay()
	@Test
	public void testPlay_Play_withPreviousCombinationCardNoMoreThanOne_testIsValidPlay() {
		
		//Test boolean previousCombination.getCardNo() != 0 branch
		Combination testValidCom1 = new Combination();
		testValidCom1.cardNo = 1;
		testPlayStub testValidPlay1 = new testPlayStub();
		testValidPlay1.previousCombination = testValidCom1;
		
		boolean resultValid1 = testValidPlay1.isValidPlay();
		assertEquals(false, resultValid1);
	}	
	
	@Test
	public void testPlay_Play_startIsTrue_andNoneCurrentTypeIsNone_testIsValidPlay() {
		//Test boolean start==true && currentType == ComboType.NONE branch
		testPlayStub testValidPlay2 = new testPlayStub();
		Combination testValidCom2 = new Combination();
		testValidPlay2.currentCombination = testValidCom2;
		
		boolean resultValid2 = testValidPlay2.isValidPlay();
		assertEquals(false, resultValid2);
	}
	
	@Test
	public void testPlay_Play_startIsTrue_andNoneCurrentTypeIsPair_testIsValidPlay() {
		//Test boolean start==true && currentType != ComboType.NONE branch
		testPlayStub testValidPlay3 = new testPlayStub();
		Combination testValidCom3 = new Combination();
		testValidCom3.addCard(new Card(0,0));
		testValidCom3.addCard(new Card(0,1));
		testValidPlay3.currentCombination = testValidCom3;
		
		boolean resultValid3 = testValidPlay3.isValidPlay();
		assertEquals(true, resultValid3);
	}
	
	@Test
	public void testPlay_Play_startIsFalse_andNoneCurrentTypeIsNone_testIsValidPlay() {
		//Test !start && previousCombination.getCardNo() == currentCombination.getCardNo()
		//&& currentType == ComboType.NONE branch
				testPlayStub testValidPlay4 = new testPlayStub();
				Combination testValidCom4 = new Combination();
				testValidCom4.addCard(new Card(2,2));
				testValidCom4.addCard(new Card(2,3));
				testValidPlay4.previousCombination = testValidCom4;
				
				Combination testValidCom5 = new Combination();
				testValidCom5.addCard(new Card(6,1));
				testValidCom5.addCard(new Card(7,3));
				testValidPlay4.currentCombination = testValidCom5;
				
				boolean resultValid4 = testValidPlay4.isValidPlay();
				assertEquals(false, resultValid4);
	}
	
	@Test
	public void testPlay_Play_startIsFalse_andNoneCurrentTypeIsTHreeOfKind_testIsValidPlay() {
		//Test !start && previousCombination.getCardNo() == currentCombination.getCardNo()
		//&& currentType.compareTo(previousType) == 1 branch
				testPlayStub testValidPlay5 = new testPlayStub();
				Combination testValidCom6 = new Combination();
				testValidCom6.addCard(new Card(1,1));
				testValidCom6.addCard(new Card(1,2));
				testValidCom6.addCard(new Card(1,3));
				testValidPlay5.previousCombination = testValidCom6;
						
				Combination testValidCom7 = new Combination();
				testValidCom7.addCard(new Card(7,0));
				testValidCom7.addCard(new Card(7,1));
				testValidCom7.addCard(new Card(7,2));
				testValidPlay5.currentCombination = testValidCom7;
					
				boolean resultValid5 = testValidPlay5.isValidPlay();
				assertEquals(true, resultValid5);				
	}
	
	@Test
	public void testPlay_Play_startIsFalse_andNoneCurrentTypeIsSingle_testIsValidPlay() {
		//Test !start && previousCombination.getCardNo() == currentCombination.getCardNo()
		//&& currentType == previousType && case == SINGLE branch
				testPlayStub testValidPlay6 = new testPlayStub();
				Combination testValidCom8 = new Combination();
				testValidCom8.addCard(new Card(8,1));
				testValidPlay6.previousCombination = testValidCom8;
						
				Combination testValidCom9 = new Combination();
				testValidCom9.addCard(new Card(9,3));
				testValidPlay6.currentCombination = testValidCom9;
					
				boolean resultValid6 = testValidPlay6.isValidPlay();
				assertEquals(true, resultValid6);	
	}
	
	@Test
	public void testPlay_Play_startIsFalse_andNoneCurrentTypeIsPair_testIsValidPlay() {
		//Test !start && previousCombination.getCardNo() == currentCombination.getCardNo()
		//&& currentType == previousType && case == PAIR branch
				testPlayStub testValidPlay7 = new testPlayStub();
				Combination testValidCom10 = new Combination();
				testValidCom10.addCard(new Card(4,0));
				testValidCom10.addCard(new Card(4,2));
				testValidPlay7.previousCombination = testValidCom10;
								
				Combination testValidCom11 = new Combination();
				testValidCom11.addCard(new Card(10,3));
				testValidCom11.addCard(new Card(10,1));
				testValidPlay7.currentCombination = testValidCom11;
							
				boolean resultValid7 = testValidPlay7.isValidPlay();
				assertEquals(true, resultValid7);	
	}
	
	@Test
	public void testPlay_Play_startIsFalse_andNoneCurrentTypeIsFullHouse_testIsValidPlay() {
		//Test !start && previousCombination.getCardNo() == currentCombination.getCardNo()
		//&& currentType == previousType && case == FULL_HOUSE branch
				testPlayStub testValidPlay8 = new testPlayStub();
				Combination testValidCom12 = new Combination();
				testValidCom12.addCard(new Card(1,1));
				testValidCom12.addCard(new Card(1,2));
				testValidCom12.addCard(new Card(1,3));
				testValidCom12.addCard(new Card(4,2));
				testValidCom12.addCard(new Card(4,3));
				testValidPlay8.previousCombination = testValidCom12;
						
				Combination testValidCom13 = new Combination();
				testValidCom13.addCard(new Card(7,0));
				testValidCom13.addCard(new Card(7,1));
				testValidCom13.addCard(new Card(7,2));
				testValidCom13.addCard(new Card(8,1));
				testValidCom13.addCard(new Card(8,2));
				testValidPlay8.currentCombination = testValidCom13;
					
				boolean resultValid8 = testValidPlay8.isValidPlay();
				assertEquals(true, resultValid8);	
	}
	
	@Test
	public void testPlay_Play_startIsFalse_andNoneCurrentTypeIsFourPlusOne_testIsValidPlay() {
		//Test !start && previousCombination.getCardNo() == currentCombination.getCardNo()
		//&& currentType == previousType && case == FOUR_PLUS_ONE branch
				testPlayStub testValidPlay9 = new testPlayStub();
				Combination testValidCom14 = new Combination();
				testValidCom14.addCard(new Card(5,0));
				testValidCom14.addCard(new Card(5,1));
				testValidCom14.addCard(new Card(5,3));
				testValidCom14.addCard(new Card(5,2));
				testValidCom14.addCard(new Card(8,2));
				testValidPlay9.previousCombination = testValidCom14;
												
				Combination testValidCom15 = new Combination();
				testValidCom15.addCard(new Card(9,2));
				testValidCom15.addCard(new Card(9,1));
				testValidCom15.addCard(new Card(9,3));
				testValidCom15.addCard(new Card(9,0));
				testValidCom15.addCard(new Card(10,0));
				testValidPlay9.currentCombination = testValidCom15;
											
				boolean resultValid9 = testValidPlay9.isValidPlay();
				assertEquals(true, resultValid9);		
	}
	
	@Test
	public void testPlay_Play_startIsFalse_andNoneCurrentTypeIsStraight_testIsValidPlay() {
		//Test !start && previousCombination.getCardNo() == currentCombination.getCardNo()
		//&& currentType == previousType && case == STRAIGHT branch
				testPlayStub testValidPlay10 = new testPlayStub();
				Combination testValidCom16 = new Combination();
				testValidCom16.addCard(new Card(0,0));
				testValidCom16.addCard(new Card(1,1));
				testValidCom16.addCard(new Card(2,3));
				testValidCom16.addCard(new Card(3,2));
				testValidCom16.addCard(new Card(4,2));
				testValidPlay10.previousCombination = testValidCom16;
												
				Combination testValidCom17 = new Combination();
				testValidCom17.addCard(new Card(6,2));
				testValidCom17.addCard(new Card(7,1));
				testValidCom17.addCard(new Card(8,3));
				testValidCom17.addCard(new Card(9,0));
				testValidCom17.addCard(new Card(10,0));
				testValidPlay10.currentCombination = testValidCom17;
											
				boolean resultValid10 = testValidPlay10.isValidPlay();
				assertEquals(true, resultValid10);					
	}
	
	@Test
	public void testPlay_Play_startIsFalse_andNoneCurrentTypeIsFlush_andPreviousCombinationHasHigherSuit_testIsValidPlay() {
		//Test !start && previousCombination.getCardNo() == currentCombination.getCardNo()
		//&& currentType == previousType && case == FLUSH branch 1
				testPlayStub testValidPlay11 = new testPlayStub();
				Combination testValidCom18 = new Combination();
				testValidCom18.addCard(new Card(2,3));
				testValidCom18.addCard(new Card(4,3));
				testValidCom18.addCard(new Card(6,3));
				testValidCom18.addCard(new Card(8,3));
				testValidCom18.addCard(new Card(10,3));
				testValidPlay11.previousCombination = testValidCom18;
														
				Combination testValidCom19 = new Combination();
				testValidCom19.addCard(new Card(1,2));
				testValidCom19.addCard(new Card(3,2));
				testValidCom19.addCard(new Card(5,2));
				testValidCom19.addCard(new Card(7,2));
				testValidCom19.addCard(new Card(9,2));
				testValidPlay11.currentCombination = testValidCom19;
													
				boolean resultValid11 = testValidPlay11.isValidPlay();
				assertEquals(false, resultValid11);	
	}
	
	@Test
	public void testPlay_Play_startIsFalse_andNoneCurrentTypeIsFlush_andCurrentCombinationHasHigherSuit_testIsValidPlay() {
		//Test !start && previousCombination.getCardNo() == currentCombination.getCardNo()
		//&& currentType == previousType && case == FLUSH branch 2
				testPlayStub testValidPlay12 = new testPlayStub();
				Combination testValidCom20 = new Combination();
				testValidCom20.addCard(new Card(2,0));
				testValidCom20.addCard(new Card(4,0));
				testValidCom20.addCard(new Card(6,0));
				testValidCom20.addCard(new Card(8,0));
				testValidCom20.addCard(new Card(10,0));
				testValidPlay12.previousCombination = testValidCom20;
																
				Combination testValidCom21 = new Combination();
				testValidCom21.addCard(new Card(1,2));
				testValidCom21.addCard(new Card(3,2));
				testValidCom21.addCard(new Card(5,2));
				testValidCom21.addCard(new Card(7,2));
				testValidCom21.addCard(new Card(9,2));
				testValidPlay12.currentCombination = testValidCom21;
															
				boolean resultValid12 = testValidPlay12.isValidPlay();
				assertEquals(true, resultValid12);	
	}
	
	@Test
	public void testPlay_Play_startIsFalse_andNoneCurrentTypeIsFlush_andCurrentCombinationHasHigherValue_testIsValidPlay() {
		//Test !start && previousCombination.getCardNo() == currentCombination.getCardNo()
		//&& currentType == previousType && case == FLUSH branch 3
				testPlayStub testValidPlay13 = new testPlayStub();
				Combination testValidCom22 = new Combination();
				testValidCom22.addCard(new Card(2,3));
				testValidCom22.addCard(new Card(4,3));
				testValidCom22.addCard(new Card(6,3));
				testValidCom22.addCard(new Card(8,3));
				testValidCom22.addCard(new Card(9,3));
				testValidPlay13.previousCombination = testValidCom22;
																
				Combination testValidCom23 = new Combination();
				testValidCom23.addCard(new Card(1,3));
				testValidCom23.addCard(new Card(3,3));
				testValidCom23.addCard(new Card(5,3));
				testValidCom23.addCard(new Card(7,3));
				testValidCom23.addCard(new Card(10,3));
				testValidPlay13.currentCombination = testValidCom23;
															
				boolean resultValid13 = testValidPlay13.isValidPlay();
				assertEquals(true, resultValid13);	
	}
	
	@Test
	public void testPlay_Play_startIsFalse_andNoneCurrentTypeIsFlush_andPreviousCombinationHasHigherValue_testIsValidPlay() {
		//Test !start && previousCombination.getCardNo() == currentCombination.getCardNo()
		//&& currentType == previousType && case == FLUSH branch 4
				testPlayStub testValidPlay14 = new testPlayStub();
				Combination testValidCom24 = new Combination();
				testValidCom24.addCard(new Card(2,3));
				testValidCom24.addCard(new Card(4,3));
				testValidCom24.addCard(new Card(6,3));
				testValidCom24.addCard(new Card(8,3));
				testValidCom24.addCard(new Card(10,3));
				testValidPlay14.previousCombination = testValidCom24;
																		
				Combination testValidCom25 = new Combination();
				testValidCom25.addCard(new Card(1,3));
				testValidCom25.addCard(new Card(3,3));
				testValidCom25.addCard(new Card(5,3));
				testValidCom25.addCard(new Card(7,3));
				testValidCom25.addCard(new Card(9,3));
				testValidPlay14.currentCombination = testValidCom25;
																	
				boolean resultValid14 = testValidPlay14.isValidPlay();
				assertEquals(false, resultValid14);	
	}
	
	@Test
	public void testPlay_Play_startIsFalse_andCurrentTypeIsNotEqualToPreviousType_testIsValidPlay() {
		//Test !start && previousCombination.getCardNo() != currentCombination.getCardNo()
		//&& currentType != previousType
				testPlayStub testValidPlay15 = new testPlayStub();
				Combination testValidCom26 = new Combination();
				testValidCom26.addCard(new Card(1,2));
				testValidCom26.addCard(new Card(2,2));
				testValidCom26.addCard(new Card(3,2));
				testValidCom26.addCard(new Card(4,2));
				testValidCom26.addCard(new Card(5,2));
				testValidPlay15.previousCombination = testValidCom26;
																		
				Combination testValidCom27 = new Combination();
				testValidCom27.addCard(new Card(4,2));	
				testValidCom27.addCard(new Card(4,1));				
				testValidCom27.addCard(new Card(4,3));				
				testValidCom27.addCard(new Card(6,0));				
				testValidCom27.addCard(new Card(6,1));				
				testValidPlay15.currentCombination = testValidCom27;
										
				boolean resultValid15 = testValidPlay15.isValidPlay();
				assertEquals(false, resultValid15);	
	}
}
