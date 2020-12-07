package testbig2pokergame;

import static org.junit.Assert.*;

import org.junit.Test;

import big2pokergame.Combination;
import big2pokergame.ComboType;
import big2pokergame.Play;
import big2pokergame.SortStrategy;
import big2pokergame.BubbleSort;
import big2pokergame.Card;
import big2pokergame.CardValueType;

public class testPlay {

	// Test case UNIT0501: Check if the program can return the currentCombination
	@Test
	public void testPlay_GetCurrentCombination() {
		Combination testGetCombination1 = new Combination();
		testGetCombination1.addCard(new Card(1, 0));
		testGetCombination1.addCard(new Card(2, 1));

		Play testGetCur = new Play();
		testGetCur.setCurrentCombination(testGetCombination1);

		Combination expectedCombination1 = testGetCur.getCurrentCombination();
		assertEquals(expectedCombination1, testGetCombination1);

	}

	// Test case UNIT0502: Check if the program can return the previousCombination
	@Test
	public void testPlay_GetPreviousCombination() {
		Combination testGetCombination2 = new Combination();
		testGetCombination2.addCard(new Card(5, 2));
		testGetCombination2.addCard(new Card(9, 3));

		Play testGetPre = new Play();
		testGetPre.setPreviousCombination(testGetCombination2);

		Combination expectedCombination2 = testGetPre.getPreviousCombination();
		assertEquals(expectedCombination2, testGetCombination2);
	}

	// Test case UNIT0503: Check if the program can release current combination
	// correctly
	@Test
	public void testPlay_ReleaseCurrentCombination() {
		Play testPlay = new Play();
		Combination testRelease = new Combination();
		testRelease.addCard(new Card(7, 2));
		testRelease.addCard(new Card(7, 1));
		testPlay.setCurrentCombination(testRelease);

		Combination actualResult = testPlay.releaseCurrentCombination();
		assertEquals(testRelease.toString(), actualResult.toString());

	}

	// Test case INTE0501: Check if the program can sort the cards of a combination
	// in an ascending order and print out the combination.
	@Test
	public void testPlay_Sort() {
		SortStrategy test = new BubbleSort();
		Play testSort = new Play(test);
		Combination testSortCombination = new Combination();
		testSortCombination.addCard(new Card(7, 1));
		testSortCombination.addCard(new Card(8, 0));
		testSortCombination.addCard(new Card(4, 3));
		testSortCombination.addCard(new Card(4, 1));
		testSortCombination.addCard(new Card(10, 2));
		testSort.setCurrentCombination(testSortCombination);
		testSort.sort();
		String expectedSort = "\ncurrentCombination={ \n" + "combination{\n" + "cardNo=5, \n"
				+ "Card{index= 17, value= 4, suit= 1, discarded= false, CardSymbol= C7}\n"
				+ "Card{index= 19, value= 4, suit= 3, discarded= false, CardSymbol= S7}\n"
				+ "Card{index= 29, value= 7, suit= 1, discarded= false, CardSymbol= CX}\n"
				+ "Card{index= 32, value= 8, suit= 0, discarded= false, CardSymbol= DJ}\n"
				+ "Card{index= 42, value= 10, suit= 2, discarded= false, CardSymbol= HK}\n" + "}, \n}\n"
				+ "previousCombination={ \n" + "combination{\n" + "cardNo=0, \n}, \n}\n";
		String resultSort = testSort.toString();
		assertEquals(expectedSort, resultSort);
	}

	// Test case INTE0502: Check if the program can return false when the value is
	// different
	@Test
	public void testPlay_IsEqual_InputIsTwoDifferentValue() {
		class Cardstub1 extends Card {
			public int getValue() {
				return 2;
			}
		}
		class Cardstub2 extends Card {
			public int getValue() {
				return 4;
			}
		}

		Play testPlay = new Play();
		Cardstub1 card = new Cardstub1();// 3D
		Cardstub2 card2 = new Cardstub2();// 3C
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		assertEquals(false, testPlay.isEqual(comb, 0, 1, CardValueType.VALUE));
	}

	// Test case INTE0503: Check if the program can return false when the suit is
	// different
	@Test
	public void testPlay_IsEqual_InputIsTwoDifferentSuit() {
		class Cardstub1 extends Card {
			public int getSuit() {
				return 2;
			}
		}
		class Cardstub2 extends Card {
			public int getSuit() {
				return 4;
			}
		}
		Play testPlay = new Play();
		Cardstub1 card = new Cardstub1();// 3D
		Cardstub2 card2 = new Cardstub2();// 3C
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		assertEquals(false, testPlay.isEqual(comb, 0, 1, CardValueType.SUIT));
	}

	// Test case INTE0504: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsSingleCard() {
		Play testPlay = new Play();
		Card card = new Card(1, 1);// 4C
		Combination comb = new Combination();
		comb.addCard(card);
		assertEquals(ComboType.SINGLE, testPlay.checkType(comb));
	}

	// Test case INTE0505: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsPair() {
		Play testPlay = new Play();
		Card card = new Card(2, 2);// 5H
		Card card2 = new Card(2, 3);// 3S
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		assertEquals(ComboType.PAIR, testPlay.checkType(comb));
	}

	// Test case INTE0506: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsThreeOfKind() {
		Play testPlay = new Play();
		Card card = new Card(1, 1);// 4C
		Card card2 = new Card(1, 2);// 4H
		Card card3 = new Card(1, 3);// 4S
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		assertEquals(ComboType.THREE_OF_KIND, testPlay.checkType(comb));
	}

	// Test case INTE0507: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsInvalidThreeOfKind() {
		Play testPlay = new Play();
		Card card = new Card(1, 1);// 4C
		Card card2 = new Card(2, 2);// 4H
		Card card3 = new Card(3, 3);// 4S
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		assertEquals(ComboType.NONE, testPlay.checkType(comb));
	}

	// Test case INTE0508: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraight() {
		Play testPlay = new Play();
		Card card = new Card(3, 1);// 3C
		Card card2 = new Card(4, 1);// 4C
		Card card3 = new Card(5, 1);// 5C
		Card card4 = new Card(6, 3);// 6S
		Card card5 = new Card(7, 3);// 7S
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.STRAIGHT, testPlay.checkType(comb));
	}

	// Test case INTE0509: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsFlush() {
		Play testPlay = new Play();
		Card card = new Card(1, 1);
		Card card2 = new Card(2, 1);
		Card card3 = new Card(3, 1);
		Card card4 = new Card(7, 1);
		Card card5 = new Card(5, 1);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0510: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraightFlush_0_9_10_11_12_branch1() {
		Play testPlay = new Play();
		Card card = new Card(0, 1);
		Card card2 = new Card(9, 1);
		Card card3 = new Card(10, 1);
		Card card4 = new Card(11, 1);
		Card card5 = new Card(12, 1);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.STRAIGHT_FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0511: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraightFlush_0_9_10_11_12_branch2() {
		Play testPlay = new Play();
		Card card = new Card(0, 2);
		Card card2 = new Card(7, 2);
		Card card3 = new Card(10, 2);
		Card card4 = new Card(11, 2);
		Card card5 = new Card(12, 2);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0512: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraightFlush_0_9_10_11_12_branch3() {
		Play testPlay = new Play();
		Card card = new Card(0, 3);
		Card card2 = new Card(9, 3);
		Card card3 = new Card(6, 3);
		Card card4 = new Card(11, 3);
		Card card5 = new Card(12, 3);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0513: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraightFlush_0_9_10_11_12_branch4() {
		Play testPlay = new Play();
		Card card = new Card(0, 0);
		Card card2 = new Card(9, 0);
		Card card3 = new Card(10, 0);
		Card card4 = new Card(2, 0);
		Card card5 = new Card(12, 0);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0514: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraightFlush_0_9_10_11_12_branch5() {
		Play testPlay = new Play();
		Card card = new Card(0, 1);
		Card card2 = new Card(9, 1);
		Card card3 = new Card(10, 1);
		Card card4 = new Card(11, 1);
		Card card5 = new Card(4, 1);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0515: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraightFlush_0_1_10_11_12_branch1() {
		Play testPlay = new Play();
		Card card = new Card(0, 0);
		Card card2 = new Card(1, 0);
		Card card3 = new Card(10, 0);
		Card card4 = new Card(11, 0);
		Card card5 = new Card(12, 0);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.STRAIGHT_FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0516: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraightFlush_0_1_10_11_12_branch2() {
		Play testPlay = new Play();
		Card card = new Card(0, 1);
		Card card2 = new Card(4, 1);
		Card card3 = new Card(10, 1);
		Card card4 = new Card(11, 1);
		Card card5 = new Card(12, 1);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0517: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraightFlush_0_1_10_11_12_branch3() {
		Play testPlay = new Play();
		Card card = new Card(0, 2);
		Card card2 = new Card(1, 2);
		Card card3 = new Card(7, 2);
		Card card4 = new Card(11, 2);
		Card card5 = new Card(12, 2);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0518: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraightFlush_0_1_10_11_12_branch4() {
		Play testPlay = new Play();
		Card card = new Card(0, 3);
		Card card2 = new Card(1, 3);
		Card card3 = new Card(10, 3);
		Card card4 = new Card(9, 3);
		Card card5 = new Card(12, 3);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0519: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraightFlush_0_1_10_11_12_branch5() {
		Play testPlay = new Play();
		Card card = new Card(0, 2);
		Card card2 = new Card(1, 2);
		Card card3 = new Card(10, 2);
		Card card4 = new Card(11, 2);
		Card card5 = new Card(8, 2);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0520: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraightFlush_0_1_2_11_12_branch1() {
		Play testPlay = new Play();
		Card card = new Card(0, 0);
		Card card2 = new Card(1, 0);
		Card card3 = new Card(2, 0);
		Card card4 = new Card(11, 0);
		Card card5 = new Card(12, 0);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.STRAIGHT_FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0521: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraightFlush_0_1_2_11_12_branch2() {
		Play testPlay = new Play();
		Card card = new Card(0, 1);
		Card card2 = new Card(6, 1);
		Card card3 = new Card(2, 1);
		Card card4 = new Card(11, 1);
		Card card5 = new Card(12, 1);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0522: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraightFlush_0_1_2_11_12_branch3() {
		Play testPlay = new Play();
		Card card = new Card(0, 2);
		Card card2 = new Card(1, 2);
		Card card3 = new Card(8, 2);
		Card card4 = new Card(11, 2);
		Card card5 = new Card(12, 2);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0523: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraightFlush_0_1_2_11_12_branch4() {
		Play testPlay = new Play();
		Card card = new Card(0, 3);
		Card card2 = new Card(1, 3);
		Card card3 = new Card(2, 3);
		Card card4 = new Card(9, 3);
		Card card5 = new Card(12, 3);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0524: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraightFlush_0_1_2_11_12_branch5() {
		Play testPlay = new Play();
		Card card = new Card(0, 0);
		Card card2 = new Card(1, 0);
		Card card3 = new Card(2, 0);
		Card card4 = new Card(11, 0);
		Card card5 = new Card(7, 0);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0525: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraightFlush_0_1_2_3_12_branch1() {
		Play testPlay = new Play();
		Card card = new Card(0, 0);
		Card card2 = new Card(1, 0);
		Card card3 = new Card(2, 0);
		Card card4 = new Card(3, 0);
		Card card5 = new Card(12, 0);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.STRAIGHT_FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0526: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraightFlush_0_1_2_3_12_branch2() {
		Play testPlay = new Play();
		Card card = new Card(0, 1);
		Card card2 = new Card(7, 1);
		Card card3 = new Card(2, 1);
		Card card4 = new Card(3, 1);
		Card card5 = new Card(12, 1);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0527: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraightFlush_0_1_2_3_12_branch3() {
		Play testPlay = new Play();
		Card card = new Card(0, 2);
		Card card2 = new Card(1, 2);
		Card card3 = new Card(5, 2);
		Card card4 = new Card(3, 2);
		Card card5 = new Card(12, 2);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0528: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraightFlush_0_1_2_3_12_branch4() {
		Play testPlay = new Play();
		Card card = new Card(0, 3);
		Card card2 = new Card(1, 3);
		Card card3 = new Card(2, 3);
		Card card4 = new Card(9, 3);
		Card card5 = new Card(12, 3);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0529: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsStraightFlush_0_1_2_3_12_branch5() {
		Play testPlay = new Play();
		Card card = new Card(0, 3);
		Card card2 = new Card(1, 3);
		Card card3 = new Card(2, 3);
		Card card4 = new Card(3, 3);
		Card card5 = new Card(6, 3);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FLUSH, testPlay.checkType(comb));
	}

	// Test case INTE0530: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsFourPlusOne() {
		Play testPlay = new Play();
		Card card = new Card(1, 0);
		Card card2 = new Card(1, 1);
		Card card3 = new Card(1, 2);
		Card card4 = new Card(1, 3);
		Card card5 = new Card(12, 1);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FOUR_PLUS_ONE, testPlay.checkType(comb));
	}

	// Test case INTE0531: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsFourPlusOneByDifferentOrder() {
		Play testPlay = new Play();
		Card card = new Card(12, 1);
		Card card2 = new Card(1, 0);
		Card card3 = new Card(1, 1);
		Card card4 = new Card(1, 2);
		Card card5 = new Card(1, 3);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FOUR_PLUS_ONE, testPlay.checkType(comb));
	}

	// Test case INTE0532: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsFullHouse() {
		Play testPlay = new Play();
		Card card = new Card(1, 0);
		Card card2 = new Card(1, 1);
		Card card3 = new Card(1, 2);
		Card card4 = new Card(2, 2);
		Card card5 = new Card(2, 3);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FULL_HOUSE, testPlay.checkType(comb));
	}

	// Test case INTE0533 Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsFullHouseByDifferentOrder() {
		Play testPlay = new Play();
		Card card = new Card(1, 0);
		Card card2 = new Card(1, 1);
		Card card3 = new Card(2, 1);
		Card card4 = new Card(2, 2);
		Card card5 = new Card(2, 3);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.FULL_HOUSE, testPlay.checkType(comb));
	}

	// Test case INTE0534: Check if the program can return correct combination type
	@Test
	public void testPlay_CheckType_InputIsNONE() {
		Play testPlay = new Play();
		Card card = new Card(1, 3);
		Card card2 = new Card(1, 0);
		Card card3 = new Card(1, 1);
		Card card4 = new Card(4, 2);
		Card card5 = new Card(9, 0);
		Combination comb = new Combination();
		comb.addCard(card);
		comb.addCard(card2);
		comb.addCard(card3);
		comb.addCard(card4);
		comb.addCard(card5);
		assertEquals(ComboType.NONE, testPlay.checkType(comb));
	}

	// Test case INTE0535: Check if the program can compare combinations correctly
	@Test
	public void testPlay_Compare() {
		Combination testPre = new Combination();
		testPre.addCard(new Card(3, 1));
		testPre.addCard(new Card(5, 2));
		testPre.addCard(new Card(6, 2));
		testPre.addCard(new Card(7, 3));

		Combination testCur = new Combination();
		testCur.addCard(new Card(4, 2));
		testCur.addCard(new Card(5, 3));
		testCur.addCard(new Card(6, 1));
		testCur.addCard(new Card(0, 0));

		Play testCompare = new Play();

		// card in currentCombination has higher value than previousCombination
		boolean resultCompare1 = testCompare.compare(testPre, testCur, 0);
		assertEquals(true, resultCompare1);

		// card in currentCombination has the same value but higher suit than
		// previousCombination
		boolean resultCompare2 = testCompare.compare(testPre, testCur, 1);
		assertEquals(true, resultCompare2);

		// card in currentCombination has the same value but lower suit than
		// previousCombination
		boolean resultCompare3 = testCompare.compare(testPre, testCur, 2);
		assertEquals(false, resultCompare3);

		// card in currentCombination has lower value than previousCombination
		boolean resultCompare4 = testCompare.compare(testPre, testCur, 3);
		assertEquals(false, resultCompare4);
	}

	// Test case INTE0536: Check if the program can validate the previousCombination
	// a cardNo of 1
	@Test
	public void testPlay_IsValidPlay_PreviousCombinationIsNotEmpty() {

		Combination testValidPreCom = new Combination();
		testValidPreCom.setCardNo(1);
		Play testValidPlay = new Play();
		testValidPlay.setPreviousCombination(testValidPreCom);

		boolean resultValid = testValidPlay.isValidPlay();
		assertEquals(false, resultValid);
	}

	// Test case INTE0537: Check if the program can validate the play of
	// currentCombination with a ComboType of NONE
	@Test
	public void testPlay_IsValidPlay_PreviousTypeIsNoneANDCurrentTypeIsNone() {
		Play testValidPlay = new Play();
		Combination testValidCurCom = new Combination();
		testValidPlay.setCurrentCombination(testValidCurCom);

		boolean resultValid = testValidPlay.isValidPlay();
		assertEquals(false, resultValid);
	}

	// Test case INTE0538: Check if the program can validate the play of
	// currentCombination with a ComboType that is NOT NONE
	@Test
	public void testPlay_IsValidPlay_PreviousTypeIsNoneANDCurrentCombinationIsNotNone() {
		Play testValidPlay = new Play();
		Combination testValidCurCom = new Combination();
		testValidCurCom.addCard(new Card(0, 0));
		testValidCurCom.addCard(new Card(0, 1));
		testValidPlay.setCurrentCombination(testValidCurCom);

		boolean resultValid = testValidPlay.isValidPlay();
		assertEquals(true, resultValid);
	}

	// Test case INTE0539: Check if the program can validate the play of
	// currentCombination with a NONE ComboType which is not same as the
	// previousCombination
	@Test
	public void testPlay_IsValidPlay_PreviousTypeIsNotNoneANDCurrentCombinationIsNone() {
		Play testValidPlay = new Play();
		Combination testValidPreCom = new Combination();
		testValidPreCom.addCard(new Card(2, 2));
		testValidPreCom.addCard(new Card(2, 3));
		testValidPlay.setPreviousCombination(testValidPreCom);

		Combination testValidCurCom = new Combination();
		testValidCurCom.addCard(new Card(6, 1));
		testValidCurCom.addCard(new Card(7, 3));
		testValidPlay.setCurrentCombination(testValidCurCom);

		boolean resultValid = testValidPlay.isValidPlay();
		assertEquals(false, resultValid);
	}

	// Test case INTE0540: Check if the program can validate the play of
	// currentCombination with a ComboType which has higher rank than the
	// previousCombination
	@Test
	public void testPlay_IsValidPlay_PreviousTypeIsStraightANDCurrentCombinationIsFullHouse() {
		Play testValidPlay = new Play();
		Combination testValidPreCom = new Combination();
		testValidPreCom.addCard(new Card(3, 0));
		testValidPreCom.addCard(new Card(4, 1));
		testValidPreCom.addCard(new Card(5, 2));
		testValidPreCom.addCard(new Card(6, 3));
		testValidPreCom.addCard(new Card(7, 2));
		testValidPlay.setPreviousCombination(testValidPreCom);

		Combination testValidCurCom = new Combination();
		testValidCurCom.addCard(new Card(9, 0));
		testValidCurCom.addCard(new Card(9, 1));
		testValidCurCom.addCard(new Card(9, 2));
		testValidCurCom.addCard(new Card(9, 3));
		testValidCurCom.addCard(new Card(2, 0));
		testValidPlay.setCurrentCombination(testValidCurCom);

		boolean resultValid = testValidPlay.isValidPlay();
		assertEquals(true, resultValid);
	}

	// Test case INTE0541: Check if the program can validate the currentCombination
	// with a ComboType SINGLE that is same as previousCombination
	@Test
	public void testPlay_IsValidPlay_BothCombinationsAreSingle() {
		Play testValidPlay = new Play();
		Combination testValidPreCom = new Combination();
		testValidPreCom.addCard(new Card(8, 1));
		testValidPlay.setPreviousCombination(testValidPreCom);

		Combination testValidCurCom = new Combination();
		testValidCurCom.addCard(new Card(9, 3));
		testValidPlay.setCurrentCombination(testValidCurCom);

		boolean resultValid = testValidPlay.isValidPlay();
		assertEquals(true, resultValid);
	}

	// Test case INTE0542: Check if the program can validate the currentCombination
	// with a ComboType PAIR that is same as previousCombination
	@Test
	public void testPlay_IsValidPlay_BothCombinationsArePair() {
		Play testValidPlay = new Play();
		Combination testValidPreCom = new Combination();
		testValidPreCom.addCard(new Card(4, 0));
		testValidPreCom.addCard(new Card(4, 2));
		testValidPlay.setPreviousCombination(testValidPreCom);

		Combination testValidCurCom = new Combination();
		testValidCurCom.addCard(new Card(10, 3));
		testValidCurCom.addCard(new Card(10, 1));
		testValidPlay.setCurrentCombination(testValidCurCom);

		boolean resultValid = testValidPlay.isValidPlay();
		assertEquals(true, resultValid);
	}

	// Test case INTE0543: Check if the program can validate the currentCombination
	// with a ComboType THREE_OF_KIND that is same as previousCombination
	@Test
	public void testPlay_IsValidPlay_BothCombinationsAreThreeOfKind() {
		Play testValidPlay = new Play();
		Combination testValidPreCom = new Combination();
		testValidPreCom.addCard(new Card(1, 0));
		testValidPreCom.addCard(new Card(1, 2));
		testValidPreCom.addCard(new Card(1, 1));
		testValidPlay.setPreviousCombination(testValidPreCom);

		Combination testValidCurCom = new Combination();
		testValidCurCom.addCard(new Card(11, 3));
		testValidCurCom.addCard(new Card(11, 3));
		testValidCurCom.addCard(new Card(11, 3));
		testValidPlay.setCurrentCombination(testValidCurCom);

		boolean resultValid = testValidPlay.isValidPlay();
		assertEquals(true, resultValid);
	}

	// Test case INTE0544: Check if the program can validate the currentCombination
	// with a ComboType FULL_HOUSE that is same as previousCombination
	@Test
	public void testPlay_IsValidPlay_BothCombinationsAreFullHouse() {
		Play testValidPlay = new Play();
		Combination testValidPreCom = new Combination();
		testValidPreCom.addCard(new Card(1, 1));
		testValidPreCom.addCard(new Card(1, 2));
		testValidPreCom.addCard(new Card(1, 3));
		testValidPreCom.addCard(new Card(4, 2));
		testValidPreCom.addCard(new Card(4, 3));
		testValidPlay.setPreviousCombination(testValidPreCom);

		Combination testValidCurCom = new Combination();
		testValidCurCom.addCard(new Card(7, 0));
		testValidCurCom.addCard(new Card(7, 1));
		testValidCurCom.addCard(new Card(7, 2));
		testValidCurCom.addCard(new Card(8, 1));
		testValidCurCom.addCard(new Card(8, 2));
		testValidPlay.setCurrentCombination(testValidCurCom);

		boolean resultValid = testValidPlay.isValidPlay();
		assertEquals(true, resultValid);
	}

	// Test case INTE0545: Check if the program can validate the currentCombination
	// with a ComboType FOUR_PLUS_ONE that is same as previousCombination
	@Test
	public void testPlay_IsValidPlay_BothCombinationsAreFourPlusOne() {
		Play testValidPlay = new Play();
		Combination testValidPreCom = new Combination();
		testValidPreCom.addCard(new Card(5, 0));
		testValidPreCom.addCard(new Card(5, 1));
		testValidPreCom.addCard(new Card(5, 3));
		testValidPreCom.addCard(new Card(5, 2));
		testValidPreCom.addCard(new Card(8, 2));
		testValidPlay.setPreviousCombination(testValidPreCom);

		Combination testValidCurCom = new Combination();
		testValidCurCom.addCard(new Card(9, 2));
		testValidCurCom.addCard(new Card(9, 1));
		testValidCurCom.addCard(new Card(9, 3));
		testValidCurCom.addCard(new Card(9, 0));
		testValidCurCom.addCard(new Card(10, 0));
		testValidPlay.setCurrentCombination(testValidCurCom);

		boolean resultValid = testValidPlay.isValidPlay();
		assertEquals(true, resultValid);
	}

	// Test case INTE0546: Check if the program can validate the currentCombination
	// with a ComboType STRAIGHT that is same as previousCombination
	@Test
	public void testPlay_IsValidPlay_BothCombinationsAreStraight() {
		Play testValidPlay = new Play();
		Combination testValidPreCom = new Combination();
		testValidPreCom.addCard(new Card(0, 0));
		testValidPreCom.addCard(new Card(1, 1));
		testValidPreCom.addCard(new Card(2, 3));
		testValidPreCom.addCard(new Card(3, 2));
		testValidPreCom.addCard(new Card(4, 2));
		testValidPlay.setPreviousCombination(testValidPreCom);

		Combination testValidCurCom = new Combination();
		testValidCurCom.addCard(new Card(6, 2));
		testValidCurCom.addCard(new Card(7, 1));
		testValidCurCom.addCard(new Card(8, 3));
		testValidCurCom.addCard(new Card(9, 0));
		testValidCurCom.addCard(new Card(10, 0));
		testValidPlay.setCurrentCombination(testValidCurCom);

		boolean resultValid = testValidPlay.isValidPlay();
		assertEquals(true, resultValid);
	}

	// Test case INTE0547: Check if the program can validate the currentCombination
	// with a ComboType FLUSH that is same as previousCombination
	@Test
	public void testPlay_IsValidPlay_BothCombinationsAreFlush1() {
		Play testValidPlay = new Play();
		Combination testValidPreCom = new Combination();
		testValidPreCom.addCard(new Card(2, 3));
		testValidPreCom.addCard(new Card(4, 3));
		testValidPreCom.addCard(new Card(6, 3));
		testValidPreCom.addCard(new Card(8, 3));
		testValidPreCom.addCard(new Card(10, 3));
		testValidPlay.setPreviousCombination(testValidPreCom);

		Combination testValidCurCom = new Combination();
		testValidCurCom.addCard(new Card(1, 2));
		testValidCurCom.addCard(new Card(3, 2));
		testValidCurCom.addCard(new Card(5, 2));
		testValidCurCom.addCard(new Card(7, 2));
		testValidCurCom.addCard(new Card(9, 2));
		testValidPlay.setCurrentCombination(testValidCurCom);

		boolean resultValid = testValidPlay.isValidPlay();
		assertEquals(false, resultValid);
	}

	// Test case INTE0548: Check if the program can validate the currentCombination
	// with a ComboType FLUSH that is same as previousCombination
	@Test
	public void testPlay_IsValidPlay_BothCombinationsAreFlush2() {
		Play testValidPlay = new Play();
		Combination testValidPreCom = new Combination();
		testValidPreCom.addCard(new Card(2, 0));
		testValidPreCom.addCard(new Card(4, 0));
		testValidPreCom.addCard(new Card(6, 0));
		testValidPreCom.addCard(new Card(8, 0));
		testValidPreCom.addCard(new Card(10, 0));
		testValidPlay.setPreviousCombination(testValidPreCom);

		Combination testValidCurCom = new Combination();
		testValidCurCom.addCard(new Card(1, 2));
		testValidCurCom.addCard(new Card(3, 2));
		testValidCurCom.addCard(new Card(5, 2));
		testValidCurCom.addCard(new Card(7, 2));
		testValidCurCom.addCard(new Card(9, 2));
		testValidPlay.setCurrentCombination(testValidCurCom);

		boolean resultValid = testValidPlay.isValidPlay();
		assertEquals(true, resultValid);
	}

	// Test case INTE0549: Check if the program can validate the currentCombination
	// with a ComboType FLUSH that is same as previousCombination
	@Test
	public void testPlay_IsValidPlay_BothCombinationsAreFlush3() {
		Play testValidPlay = new Play();
		Combination testValidPreCom = new Combination();
		testValidPreCom.addCard(new Card(2, 3));
		testValidPreCom.addCard(new Card(4, 3));
		testValidPreCom.addCard(new Card(6, 3));
		testValidPreCom.addCard(new Card(8, 3));
		testValidPreCom.addCard(new Card(9, 3));
		testValidPlay.setPreviousCombination(testValidPreCom);

		Combination testValidCurCom = new Combination();
		testValidCurCom.addCard(new Card(1, 3));
		testValidCurCom.addCard(new Card(3, 3));
		testValidCurCom.addCard(new Card(5, 3));
		testValidCurCom.addCard(new Card(7, 3));
		testValidCurCom.addCard(new Card(10, 3));
		testValidPlay.setCurrentCombination(testValidCurCom);

		boolean resultValid = testValidPlay.isValidPlay();
		assertEquals(true, resultValid);
	}

	// Test case INTE0550: Check if the program can validate the currentCombination
	// with a ComboType FLUSH that is same as previousCombination
	@Test
	public void testPlay_IsValidPlay_BothCombinationsAreFlush4() {
		Play testValidPlay = new Play();
		Combination testValidPreCom = new Combination();
		testValidPreCom.addCard(new Card(2, 3));
		testValidPreCom.addCard(new Card(4, 3));
		testValidPreCom.addCard(new Card(6, 3));
		testValidPreCom.addCard(new Card(8, 3));
		testValidPreCom.addCard(new Card(10, 3));
		testValidPlay.setPreviousCombination(testValidPreCom);

		Combination testValidCurCom = new Combination();
		testValidCurCom.addCard(new Card(1, 3));
		testValidCurCom.addCard(new Card(3, 3));
		testValidCurCom.addCard(new Card(5, 3));
		testValidCurCom.addCard(new Card(7, 3));
		testValidCurCom.addCard(new Card(9, 3));
		testValidPlay.setCurrentCombination(testValidCurCom);

		boolean resultValid = testValidPlay.isValidPlay();
		assertEquals(false, resultValid);
	}

	// Test case INTE0551: Check if the program can validate the currentCombination
	// with a ComboType STRAIGHT_FLUSH that is same as previousCombination
	@Test
	public void testPlay_IsValidPlay_BothCombinationsAreStraightFlush() {
		Play testValidPlay = new Play();
		Combination testValidPreCom = new Combination();
		testValidPreCom.addCard(new Card(3, 2));
		testValidPreCom.addCard(new Card(4, 2));
		testValidPreCom.addCard(new Card(5, 2));
		testValidPreCom.addCard(new Card(6, 2));
		testValidPreCom.addCard(new Card(7, 2));
		testValidPlay.setPreviousCombination(testValidPreCom);

		Combination testValidCurCom = new Combination();
		testValidCurCom.addCard(new Card(7, 3));
		testValidCurCom.addCard(new Card(8, 3));
		testValidCurCom.addCard(new Card(9, 3));
		testValidCurCom.addCard(new Card(10, 3));
		testValidCurCom.addCard(new Card(11, 3));
		testValidPlay.setCurrentCombination(testValidCurCom);

		boolean resultValid = testValidPlay.isValidPlay();
		assertEquals(true, resultValid);
	}

	// Test case INTE0552: Check if the program can validate the play of
	// currentCombination with a ComboType that is not same as the
	// previousCombination
	@Test
	public void testPlay_IsValidPlay_BothCombinationsAreDifferent() {
		Play testValidPlay = new Play();
		Combination testValidPreCom = new Combination();
		testValidPreCom.addCard(new Card(1, 2));
		testValidPreCom.addCard(new Card(2, 2));
		testValidPreCom.addCard(new Card(3, 2));
		testValidPreCom.addCard(new Card(4, 2));
		testValidPreCom.addCard(new Card(5, 2));
		testValidPlay.setPreviousCombination(testValidPreCom);

		Combination testValidCurCom = new Combination();
		testValidCurCom.addCard(new Card(4, 2));
		testValidCurCom.addCard(new Card(4, 1));
		testValidCurCom.addCard(new Card(4, 3));
		testValidCurCom.addCard(new Card(6, 0));
		testValidCurCom.addCard(new Card(6, 1));
		testValidPlay.setCurrentCombination(testValidCurCom);

		boolean resultValid = testValidPlay.isValidPlay();
		assertEquals(false, resultValid);
	}

	// Test case INTE0553: Check if the program can validate the play of
	// currentCombination with a ComboType NONEthat is same as the
	// previousCombination
	@Test
	public void testPlay_IsValidPlay_BothCombinationsAreNone() {
		Play testValidPlay = new Play();
		Combination testValidPreCom = new Combination();
		testValidPreCom.addCard(new Card(1, 1));
		testValidPreCom.addCard(new Card(3, 2));
		testValidPreCom.addCard(new Card(5, 3));
		testValidPreCom.addCard(new Card(7, 0));
		testValidPreCom.addCard(new Card(9, 1));
		testValidPlay.setPreviousCombination(testValidPreCom);

		Combination testValidCurCom = new Combination();
		testValidCurCom.addCard(new Card(2, 2));
		testValidCurCom.addCard(new Card(4, 1));
		testValidCurCom.addCard(new Card(6, 3));
		testValidCurCom.addCard(new Card(8, 0));
		testValidCurCom.addCard(new Card(10, 1));
		testValidPlay.setCurrentCombination(testValidCurCom);

		boolean resultValid = testValidPlay.isValidPlay();
		assertEquals(false, resultValid);
	}

}
