import static org.junit.Assert.*;

import org.junit.Test;

public class testPlayMabel {

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
