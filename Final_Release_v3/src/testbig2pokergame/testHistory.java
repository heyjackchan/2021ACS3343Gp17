package testbig2pokergame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import big2pokergame.Card;
import big2pokergame.Combination;
import big2pokergame.History;

public class testHistory {

	// UNIT TESTS
	// Test case UNIT0701: check if the getRound() function in the history class is
	// getting the correct value or not.
	@Test
	public void testHistory_getRound_noCondition() {
		int round = 10;
		int turn = 4;
		String playerName = "hello";
		Combination currentPlay = null;
		int round1 = 10;
		History history = new History(round, turn, playerName, currentPlay);

		assertEquals(history.getRound(), round1);

	}

	// Test case UNIT0702: check if the getTurn() function in the history class is
	// getting the correct value or not.
	@Test
	public void testHistory_getTurn_noCondition() {
		int round = 10;
		int turn = 4;
		String playerName = "hello";
		Combination currentPlay = null;
		int turn1 = 4;
		History history = new History(round, turn, playerName, currentPlay);

		assertEquals(history.getTurn(), turn1);
	}

	// Test case UNIT0703: check if the getPlayerName() function in the history
	// class is getting the correct value or not.
	@Test
	public void testHistory_getPlayerName_noCondition() {
		int round = 10;
		int turn = 4;
		String playerName = "hello";
		Combination currentPlay = null;

		String playerName1 = "hello";
		History history = new History(round, turn, playerName, currentPlay);

		assertEquals(history.getPlayerName(), playerName1);

	}

	// INTEGRATION TESTS
	// Test case INTE0701: check if the printCurrentPlaySkip() function in the
	// history class is printing the correct value or not.
	@Test
	public void testHistory_printCurrentPlay_skip() {
		int round = 10;
		int turn = 4;
		String playerName = "hello";
		Combination currentPlay = null;
		String currentPlay1 = "SKIP";
		History history = new History(round, turn, playerName, currentPlay);

		assertEquals(history.printCurrentPlay(), currentPlay1);
	}

	// Test case INTE0702: check if the condition when currentPlayGetCardNoEqual5 in
	// printCurrentPlaySkip() function is correct or not.
	@Test
	public void testHistory_printCurrentPlay_getCardNoEqual5() {
		Card card1 = new Card(0, 0);
		Card card2 = new Card(1, 0);
		Card card3 = new Card(2, 0);
		Card card4 = new Card(3, 0);
		Card card5 = new Card(4, 0);// create a hard coded Card (D3), value = 0 means ��3��, suit = 0 means diamond
									// (D)
		Combination currentPlay = new Combination(); // create a empty combination with capacity = 5
		currentPlay.addCard(card1); // add that card into combination, add one card each time
		currentPlay.addCard(card2);
		currentPlay.addCard(card3);
		currentPlay.addCard(card4);
		currentPlay.addCard(card5);
		History history = new History(1, 1, "Alice", currentPlay); // create the history object
		String actualResult = history.printCurrentPlay();
		String expectedResult = "D3, D4, D5, D6, D7"; // fill in your expected result inside ����
		assertEquals(expectedResult, actualResult);
	}

	// Test case INTE0703: check if the condition when currentPlayGetCardNoEqual1 in
	// printCurrentPlaySkip() function is correct or not.
	@Test
	public void testHistory_printCurrentPlay_getCardNoEqual1() {
		// History history = new History(round, turn, playerName, currentPlay);
		Card card = new Card(0, 0); // create a hard coded Card (D3), value = 0 means ��3��, suit = 0 means diamond
									// (D)
		Combination currentPlay = new Combination(); // create a empty combination with capacity = 5
		currentPlay.addCard(card); // add that card into combination, add one card each time
		History history = new History(1, 1, "Alice", currentPlay); // create the history object
		String actualResult = history.printCurrentPlay();
		String expectedResult = "D3"; // fill in your expected result inside ����
		assertEquals(expectedResult, actualResult);

	}

	// Test case INTE0704: check if the condition when currentPlayGetCardNoEqual2 in
	// printCurrentPlaySkip() function is correct or not.
	@Test
	public void testHistory_printCurrentPlay_getCardNoLargerEqual2() {

		// History history = new History(round, turn, playerName, currentPlay);
		Card card1 = new Card(0, 0);
		Card card2 = new Card(1, 0);// create a hard coded Card (D3), value = 0 means ��3��, suit = 0 means diamond
									// (D)
		Combination currentPlay = new Combination(); // create a empty combination with capacity = 5
		currentPlay.addCard(card1);
		currentPlay.addCard(card2);// add that card into combination, add one card each time
		History history = new History(1, 1, "Alice", currentPlay); // create the history object
		String actualResult = history.printCurrentPlay();
		String expectedResult = "D3, D4"; // fill in your expected result inside ����
		assertEquals(expectedResult, actualResult);
	}

}