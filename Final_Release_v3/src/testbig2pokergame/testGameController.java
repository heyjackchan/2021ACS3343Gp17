package testbig2pokergame;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import big2pokergame.AbstractPlayer;
import big2pokergame.Card;
import big2pokergame.Combination;
import big2pokergame.Dealer;
import big2pokergame.GameController;
import big2pokergame.Hand;
import big2pokergame.History;
import big2pokergame.HumanPlayer;
import big2pokergame.Play;

class testGameController {
	public ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@BeforeEach
	void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));

	}

	@AfterEach
	void tearDown() throws Exception {
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
	}

	// UNIT TESTS
	// Test case UNIT0901: Check if the inputting index task in promptPlayerInput
	// function is correct or not.
	@Test
	void testGameController_promptPlayerInput_withStub() {
		class GameControllerStub extends GameController {
			public String input = "123";

			public InputStream testIn = new ByteArrayInputStream(input.getBytes());
			public Scanner testsc = new Scanner(testIn);

			public GameControllerStub() {
				super(null, null, 0, 1, 1, 0, true);

				setScanner(testsc);
			}

			public String testPromptPlayerInput() {
				System.setIn(testIn);
				String input1 = promptPlayerInput();
				System.setIn(System.in);
				return input1;
			}

		}
		GameControllerStub gcStub = new GameControllerStub();

		String expected_result = "123";
		String actual_result = gcStub.testPromptPlayerInput();
		assertEquals(expected_result, actual_result);
	}

	// Test case UNIT0902: To test the printWinningMessages method can function
	// correctly or not.
	@Test
	void testGameController_printWinningMessages_withStub() {
		class GameControllerStub extends GameController {
			public GameControllerStub() {
				super(null, null, 1, 12, 2, 0, false);
				this.currentPlayer = new HumanPlayer();
				this.currentPlayer.setName("Alice");
			}

			public void pause() {
				return;
			}

			public void printRemainingNumberOfCards() {
				return;
			}
		}
		GameControllerStub gcStub = new GameControllerStub();
		gcStub.printWinningMessages();
		String expected_result = "Congratulations Alice! you win the game!\r\n" + "Game End!!!";
		assertEquals(expected_result, outContent.toString().trim());

	}

	// Test case UNIT0903: To test the play method can function correctly or not.
	@Test
	void testGameController_play_withStub() {
		class GameControllerStub extends GameController {
			public GameControllerStub() {
				super(null, null, 0, 1, 1, 0, true);
			}

			public void printRoundInfo() {
				return;
			}

			public void printRemainingNumberOfCards() {
				return;
			}

			public void printLastHistory() {
				return;
			}

			public void handleTurn() {
				this.endGame = true;
				return;
			}
		}

		GameControllerStub gcStub = new GameControllerStub();
		gcStub.play();
	}

	// Test case UNIT0904: To test the incrementRound method can function correctly
	// or not when the current turn number is one. It should not increment the
	// round.
	@Test
	void testGameController_incrementRound_turnIsOne() {
		class GameControllerStub extends GameController {
			public GameControllerStub() {
				super(null, null, 0, 1, 1, 0, true);
			}

			public int getRound() {
				return this.round;
			}
		}
		GameControllerStub gcStub = new GameControllerStub();
		gcStub.incrementRound();
		int actualResult = gcStub.getRound();
		int expectedResult = 1;
		assertEquals(expectedResult, actualResult);

	}

	// Test case UNIT0905: To test the incrementRound method can function correctly
	// or not when the current turn number is four. It should not increment the
	// round also.
	@Test
	void testGameController_incrementRound_turnIsFour() {
		class GameControllerStub extends GameController {
			public GameControllerStub() {
				super(null, null, 0, 1, 4, 0, true);
			}

			public int getRound() {
				return this.round;
			}
		}
		GameControllerStub gcStub = new GameControllerStub();
		gcStub.incrementRound();
		int actualResult = gcStub.getRound();
		int expectedResult = 1;
		assertEquals(expectedResult, actualResult);

	}

	// Test case UNIT0906: To test the incrementRound method can function correctly
	// or not when the current turn number is five. It should increment the round .
	@Test
	void testGameController_incrementRound_turnIsGreaterThanFour() {
		class GameControllerStub extends GameController {
			public GameControllerStub() {
				super(null, null, 0, 1, 5, 0, true);
			}

			public int getRound() {
				return this.round;
			}
		}
		GameControllerStub gcStub = new GameControllerStub();
		gcStub.incrementRound();
		int actualResult = gcStub.getRound();
		int expectedResult = 2;
		assertEquals(expectedResult, actualResult);

	}

	// Test case UNIT0907: To check whether the current player index can increment
	// so that the system can switch from the first player to the second player.
	@Test
	void testGameController_nextplayerIndex_currentIndexIsZero() {
		class GameControllerStub extends GameController {
			public GameControllerStub() {
				super(null, null, 0, 1, 2, 0, false);
			}
		}
		GameControllerStub gcStub = new GameControllerStub();
		int actualResult = gcStub.nextPlayerIndex();
		int expectedResult = 1;
		assertEquals(expectedResult, actualResult);
	}

	// Test case UNIT0908: To check whether the current player index can increment
	// so that the system can switch from the second player to the third player.
	@Test
	void testGameController_nextplayerIndex_currentIndexIsOne() {
		class GameControllerStub extends GameController {
			public GameControllerStub() {
				super(null, null, 1, 1, 2, 0, false);
			}
		}
		GameControllerStub gcStub = new GameControllerStub();
		int actualResult = gcStub.nextPlayerIndex();
		int expectedResult = 2;
		assertEquals(expectedResult, actualResult);
	}

	// Test case UNIT0909: To check whether the current player index can change back
	// to zero so that the system can switch from the fourth player to the first
	// player.
	@Test
	void testGameController_nextplayerIndex_currentIndexIsThree() {
		class GameControllerStub extends GameController {
			public GameControllerStub() {
				super(null, null, 3, 1, 2, 0, false);
			}
		}
		GameControllerStub gcStub = new GameControllerStub();
		int actualResult = gcStub.nextPlayerIndex();
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	// Test case UNIT0910: To test whether the player can play cards by choosing
	// action "1" or not.
	@Test
	void testGameController_handleTurn_playerChoosePlayCards() {
		class GameControllerStub extends GameController {
			public String name = "4\n1";
			public InputStream testIn = new ByteArrayInputStream(name.getBytes());
			public Scanner testsc = new Scanner(testIn);

			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				setScanner(testsc);
			}

			public void testHandleTurn() {
				try {
					System.setIn(testIn);
					handleTurn();

				} finally {
					System.setIn(System.in);
				}
			}

			public void printPlayerHand(int index) {
				return;
			}

			public void discard() {
				return;
			}
		}
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}
		GameControllerStub gcStub = new GameControllerStub(players);
		gcStub.testHandleTurn();
	}

	// Test case UNIT0911: To test whether the player can skip by choosing action
	// "0" or not.
	@Test
	void testGameController_handleTurn_playerChooseSkip() {
		class GameControllerStub extends GameController {
			public String name = "0";
			public InputStream testIn = new ByteArrayInputStream(name.getBytes());
			public Scanner testsc = new Scanner(testIn);

			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				setScanner(testsc);
			}

			public void testHandleTurn() {
				try {
					System.setIn(testIn);
					handleTurn();

				} finally {
					System.setIn(System.in);
				}
			}

			public void printPlayerHand(int index) {
				return;
			}

			public void skip() {
				return;
			}
		}
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}
		GameControllerStub gcStub = new GameControllerStub(players);
		gcStub.testHandleTurn();
	}

	// Test case UNIT0912: To test whether the player can viewHistories by choosing
	// action "2" or not.
	@Test
	void testGameController_handleTurn_playerChooseViewHistories() {
		class GameControllerStub extends GameController {
			public String name = "2";
			public InputStream testIn = new ByteArrayInputStream(name.getBytes());
			public Scanner testsc = new Scanner(testIn);

			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				setScanner(testsc);
			}

			public void testHandleTurn() {
				try {
					System.setIn(testIn);
					handleTurn();

				} finally {
					System.setIn(System.in);
				}
			}

			public void printPlayerHand(int index) {
				return;
			}

			public void printHistories() {
				return;
			}
		}
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}
		GameControllerStub gcStub = new GameControllerStub(players);
		gcStub.testHandleTurn();
	}

	// Test case UNIT0913: To test whether the checkMenuInput can return true when
	// the player enters "0" to skip his/her turn.
	@Test
	void testGameController_checkmenuInput_isValidWhenInputIsZero() {
		GameController gc = GameController.getInstance();
		boolean actualResult = gc.checkMenuInput("0");
		assertEquals(true, actualResult);
	}

	// Test case UNIT0914: To test whether the checkMenuInput can return true when
	// the player enters "1" to skip his/her turn.
	@Test
	void testGameController_checkmenuInput_isValidWhenInputIOne() {
		GameController gc = GameController.getInstance();
		boolean actualResult = gc.checkMenuInput("1");
		assertEquals(true, actualResult);
	}

	// Test case UNIT0915: To test whether the checkMenuInput can return true when
	// the player enters "2" to skip his/her turn.
	@Test
	void testGameController_checkmenuInput_isValidWhenInputIsTwo() {
		GameController gc = GameController.getInstance();
		boolean actualResult = gc.checkMenuInput("2");
		assertEquals(true, actualResult);
	}

	// Test case UNIT0916: To test whether the checkMenuInput can return false when
	// the player enters invalid input.
	@Test
	void testGameController_checkmenuInput_isNotValid() {
		GameController gc = GameController.getInstance();
		boolean actualResult = gc.checkMenuInput("abc");
		assertEquals(false, actualResult);
	}

	// Test case UNIT0917: To test whether the player can skip correctly or not.
	@Test
	void testGameController_skip_playerCanSkip() {
		class GameControllerStub extends GameController {
			protected String data = "\n";
			protected InputStream testIn = new ByteArrayInputStream(data.getBytes());
			protected Scanner testSc = new Scanner(testIn);

			public GameControllerStub() {
				super(null, null, 0, 1, 2, 1, false);
				this.currentPlayer = new HumanPlayer();
				this.currentPlayer.setName("Bob");
				setScanner(testSc);
			}

			public void testSkip() {
				try {
					System.setIn(testIn);
					skip();
				} finally {
					System.setIn(System.in);
				}
			}

			public int getSkipCount() {
				return skipCount;
			}

			public int getTurn() {
				return turn;
			}

			public int getCurrentPlayerIndex() {
				return currentPlayerIndex;
			}

			public void handleSkipPlay() {
				return;
			}

			public void addHistory(String playerName, Combination currentPlay) {
				return;
			}
		}
		GameControllerStub gcStub = new GameControllerStub();
		gcStub.testSkip();
		int expectedSkipCount = 2;
		int expectedTurn = 3;
		int expectedCurentPlayerIndex = 1;
		assertEquals(expectedSkipCount, gcStub.getSkipCount());
		assertEquals(expectedTurn, gcStub.getTurn());
		assertEquals(expectedCurentPlayerIndex, gcStub.getCurrentPlayerIndex());

	}

	// Test case UNIT0918: To test whether the first player can skip or not. It
	// should print error message to tell the first player you cannot skip.
	@Test
	void testGameController_skip_playerCannotSkipAsIsFirstPlayer() {
		class GameControllerStub extends GameController {
			protected String data = "\n";
			protected InputStream testIn = new ByteArrayInputStream(data.getBytes());
			protected Scanner testSc = new Scanner(testIn);

			public GameControllerStub() {
				super(null, null, 0, 1, 1, 0, true);
				setScanner(testSc);
			}

			public void testSkip() {
				try {
					System.setIn(testIn);
					skip();
				} finally {
					System.setIn(System.in);
				}
			}
		}
		GameControllerStub gcStub = new GameControllerStub();
		gcStub.testSkip();
		String expectedResult = "Your cannot skip! Please choose again!\r\n" + "Press Enter Key To Continue...";
		assertEquals(expectedResult, outContent.toString().trim());

	}

	// Test case UNIT0919: To test after three consecutive players who skip the next
	// player can skip or not. It should print error message to tell the that player
	// you cannot skip.
	@Test
	void testGameController_skip_playerCannotSkipAsSkipBackToOriginalPlayer() {

		class GameControllerStub extends GameController {
			protected String data = "\n";
			protected InputStream testIn = new ByteArrayInputStream(data.getBytes());
			protected Scanner testSc = new Scanner(testIn);

			public GameControllerStub() {
				super(null, null, 0, 1, 1, 4, true);
				setScanner(testSc);
			}

			public void testSkip() {
				try {
					System.setIn(testIn);
					skip();
				} finally {
					System.setIn(System.in);
				}
			}
		}
		GameControllerStub gcStub = new GameControllerStub();
		gcStub.testSkip();
		String expectedResult = "Your cannot skip! Please choose again!\r\n" + "Press Enter Key To Continue...";
		assertEquals(expectedResult, outContent.toString().trim());

	}

	// Test case UNIT0920: To test whether the system can pause correctly.
	@Test
	void testGameController_pause_withStub() {
		class GameControllerStub extends GameController {
			protected String data = "\n";
			protected InputStream testIn = new ByteArrayInputStream(data.getBytes());
			protected Scanner testSc = new Scanner(testIn);

			public GameControllerStub() {
				super(null, null, 0, 1, 1, 0, true);
				setScanner(testSc);
			}

			public void testPause() {
				try {
					System.setIn(testIn);
					pause();

				} finally {
					System.setIn(System.in);
				}
			}

		}
		GameControllerStub gcStub = new GameControllerStub();
		gcStub.testPause();
		String expectedResult = "Press Enter Key To Continue...";
		assertEquals(expectedResult, outContent.toString().trim());
	}

	// Test case UNIT0921: check if the clearScreen function is correct or not.
	@Test
	void testGameController_clearScreen_withStub() {
		GameController gc = GameController.getInstance();
		gc.clearScreen();
	}

	// INTEGRATION TESTS

	// Test case INTE0901: To check the deal method of the dealer class can deliver
	// cards correctly to each player's hand.
	@Test
	void testGameController_deal_withStub() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
			}
		}
		Dealer dealer = new Dealer();
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				dealer.deal(players[i].getHand());
			}

			players[i].sort();
		}
		GameControllerStub gcStub = new GameControllerStub(players);
		String expectedResult = "Cards that you have: \n"
				+ ".------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.\n"
				+ "|3 /\\  |     |4 _   |     |5_  _ |     |6 .   |     |7 /\\  |     |8 _   |     |9_  _ |     |X .   |     |J /\\  |     |Q _   |     |K_  _ |     |A .   |     |2 /\\  |\n"
				+ "| /  \\ |     | ( )  |     |( \\/ )|     | / \\  |     | /  \\ |     | ( )  |     |( \\/ )|     | / \\  |     | /  \\ |     | ( )  |     |( \\/ )|     | / \\  |     | /  \\ |\n"
				+ "| \\  / |     |(_x_) |     | \\  / |     |(_,_) |     | \\  / |     |(_x_) |     | \\  / |     |(_,_) |     | \\  / |     |(_x_) |     | \\  / |     |(_,_) |     | \\  / |\n"
				+ "|  \\/ 3|     |  Y  4|     |  \\/ 5|     |  I  6|     |  \\/ 7|     |  Y  8|     |  \\/ 9|     |  I  X|     |  \\/ J|     |  Y  Q|     |  \\/ K|     |  I  A|     |  \\/ 2|\n"
				+ "'------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'\n"
				+ "\n"
				+ "|   1  |     |   2  |     |   3  |     |   4  |     |   5  |     |   6  |     |   7  |     |   8  |     |   9  |     |  10  |     |  11  |     |  12  |     |  13  |\n"
				+ "|------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|";
		gcStub.printPlayerHand(0);
		assertEquals(expectedResult, outContent.toString().trim());
	}

	// Test case INTE0902: check if the inputting player name task in
	// promptPlayerName function is correct or not .
	@Test
	void testGameController_promptPlayerName_withStub() {
		class GameControllerStub extends GameController {
			public String name = "Alice\nBob\nCandy\nDavid\n";

			public InputStream testIn = new ByteArrayInputStream(name.getBytes());
			public Scanner testsc = new Scanner(testIn);

			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				setScanner(testsc);
			}

			public void testPromptPlayerName() {
				try {
					System.setIn(testIn);
					promptPlayerName();

				} finally {
					System.setIn(System.in);
				}
			}
		}

		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}
		GameControllerStub gcStub = new GameControllerStub(players);

		gcStub.testPromptPlayerName();

		String expected_output1 = "Alice";
		assertEquals(expected_output1, players[0].getName());
		String expected_output2 = "Bob";
		assertEquals(expected_output2, players[1].getName());
		String expected_output3 = "Candy";
		assertEquals(expected_output3, players[2].getName());
		String expected_output4 = "David";
		assertEquals(expected_output4, players[3].getName());

	}

	// Test case INTE0903: To test whether the printPlayerHand correctly prints the
	// cards in that player's hand when it ends with club.
	@Test
	void testGameController_printPlayerHand_handEndWithClub() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
			}
		}
		Dealer dealer = new Dealer();
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				dealer.deal(players[i].getHand());
			}

			players[i].sort();
		}
		GameControllerStub gcStub = new GameControllerStub(players);
		String expectedResult = "Cards that you have: \n"
				+ ".------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.\n"
				+ "|3 _   |     |4_  _ |     |5 .   |     |6 /\\  |     |7 _   |     |8_  _ |     |9 .   |     |X /\\  |     |J _   |     |Q_  _ |     |K .   |     |A /\\  |     |2 _   |\n"
				+ "| ( )  |     |( \\/ )|     | / \\  |     | /  \\ |     | ( )  |     |( \\/ )|     | / \\  |     | /  \\ |     | ( )  |     |( \\/ )|     | / \\  |     | /  \\ |     | ( )  |\n"
				+ "|(_x_) |     | \\  / |     |(_,_) |     | \\  / |     |(_x_) |     | \\  / |     |(_,_) |     | \\  / |     |(_x_) |     | \\  / |     |(_,_) |     | \\  / |     |(_x_) |\n"
				+ "|  Y  3|     |  \\/ 4|     |  I  5|     |  \\/ 6|     |  Y  7|     |  \\/ 8|     |  I  9|     |  \\/ X|     |  Y  J|     |  \\/ Q|     |  I  K|     |  \\/ A|     |  Y  2|\n"
				+ "'------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'\n"
				+ "\n"
				+ "|   1  |     |   2  |     |   3  |     |   4  |     |   5  |     |   6  |     |   7  |     |   8  |     |   9  |     |  10  |     |  11  |     |  12  |     |  13  |\n"
				+ "|------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|";
		gcStub.printPlayerHand(1);
		assertEquals(expectedResult, outContent.toString().trim());
	}

	// Test case INTE0904: To test whether the printPlayerHand correctly prints the
	// cards in that player's hand when it ends with heart.
	@Test
	void testGameController_printPlayerHand_handEndWithHeart() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
			}
		}
		Dealer dealer = new Dealer();
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				dealer.deal(players[i].getHand());
			}

			players[i].sort();
		}
		GameControllerStub gcStub = new GameControllerStub(players);
		String expectedResult = "Cards that you have: \n"
				+ ".------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.\n"
				+ "|3_  _ |     |4 .   |     |5 /\\  |     |6 _   |     |7_  _ |     |8 .   |     |9 /\\  |     |X _   |     |J_  _ |     |Q .   |     |K /\\  |     |A _   |     |2_  _ |\n"
				+ "|( \\/ )|     | / \\  |     | /  \\ |     | ( )  |     |( \\/ )|     | / \\  |     | /  \\ |     | ( )  |     |( \\/ )|     | / \\  |     | /  \\ |     | ( )  |     |( \\/ )|\n"
				+ "| \\  / |     |(_,_) |     | \\  / |     |(_x_) |     | \\  / |     |(_,_) |     | \\  / |     |(_x_) |     | \\  / |     |(_,_) |     | \\  / |     |(_x_) |     | \\  / |\n"
				+ "|  \\/ 3|     |  I  4|     |  \\/ 5|     |  Y  6|     |  \\/ 7|     |  I  8|     |  \\/ 9|     |  Y  X|     |  \\/ J|     |  I  Q|     |  \\/ K|     |  Y  A|     |  \\/ 2|\n"
				+ "'------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'\n"
				+ "\n"
				+ "|   1  |     |   2  |     |   3  |     |   4  |     |   5  |     |   6  |     |   7  |     |   8  |     |   9  |     |  10  |     |  11  |     |  12  |     |  13  |\n"
				+ "|------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|";
		gcStub.printPlayerHand(2);
		assertEquals(expectedResult, outContent.toString().trim());
	}

	// Test case INTE0905: To test whether the printPlayerHand correctly prints the
	// cards in that player's hand when it ends with spade.
	@Test
	void testGameController_printPlayerHand_handEndWithSpade() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
			}
		}
		Dealer dealer = new Dealer();
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				dealer.deal(players[i].getHand());
			}

			players[i].sort();
		}
		GameControllerStub gcStub = new GameControllerStub(players);
		String expectedResult = "Cards that you have: \n"
				+ ".------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.\n"
				+ "|3 .   |     |4 /\\  |     |5 _   |     |6_  _ |     |7 .   |     |8 /\\  |     |9 _   |     |X_  _ |     |J .   |     |Q /\\  |     |K _   |     |A_  _ |     |2 .   |\n"
				+ "| / \\  |     | /  \\ |     | ( )  |     |( \\/ )|     | / \\  |     | /  \\ |     | ( )  |     |( \\/ )|     | / \\  |     | /  \\ |     | ( )  |     |( \\/ )|     | / \\  |\n"
				+ "|(_,_) |     | \\  / |     |(_x_) |     | \\  / |     |(_,_) |     | \\  / |     |(_x_) |     | \\  / |     |(_,_) |     | \\  / |     |(_x_) |     | \\  / |     |(_,_) |\n"
				+ "|  I  3|     |  \\/ 4|     |  Y  5|     |  \\/ 6|     |  I  7|     |  \\/ 8|     |  Y  9|     |  \\/ X|     |  I  J|     |  \\/ Q|     |  Y  K|     |  \\/ A|     |  I  2|\n"
				+ "'------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'\n"
				+ "\n"
				+ "|   1  |     |   2  |     |   3  |     |   4  |     |   5  |     |   6  |     |   7  |     |   8  |     |   9  |     |  10  |     |  11  |     |  12  |     |  13  |\n"
				+ "|------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|";
		gcStub.printPlayerHand(3);
		assertEquals(expectedResult, outContent.toString().trim());
	}

	// Test case INTE0906: To test whether the printPlayerHand correctly prints the
	// cards in that player's hand when it ends with diamond and some cards are
	// discarded.
	@Test
	void testGameController_printPlayerHand_handEndWithDiamondAndSomeCardsAreDiscarded() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
			}
		}
		Dealer dealer = new Dealer();
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				dealer.deal(players[i].getHand());
			}

			players[i].sort();
		}
		Hand playerOneHand = players[0].getHand();
		playerOneHand.releaseCard(1);
		playerOneHand.releaseCard(12);
		GameControllerStub gcStub = new GameControllerStub(players);
		String expectedResult = "Cards that you have: \n"
				+ ".------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.     .------.\n"
				+ "|3 /\\  |     |######|     |5_  _ |     |6 .   |     |7 /\\  |     |8 _   |     |9_  _ |     |X .   |     |J /\\  |     |Q _   |     |K_  _ |     |A .   |     |######|\n"
				+ "| /  \\ |     |######|     |( \\/ )|     | / \\  |     | /  \\ |     | ( )  |     |( \\/ )|     | / \\  |     | /  \\ |     | ( )  |     |( \\/ )|     | / \\  |     |######|\n"
				+ "| \\  / |     |######|     | \\  / |     |(_,_) |     | \\  / |     |(_x_) |     | \\  / |     |(_,_) |     | \\  / |     |(_x_) |     | \\  / |     |(_,_) |     |######|\n"
				+ "|  \\/ 3|     |######|     |  \\/ 5|     |  I  6|     |  \\/ 7|     |  Y  8|     |  \\/ 9|     |  I  X|     |  \\/ J|     |  Y  Q|     |  \\/ K|     |  I  A|     |######|\n"
				+ "'------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'     '------'\n"
				+ "\n"
				+ "|   1  |     |  XX  |     |   3  |     |   4  |     |   5  |     |   6  |     |   7  |     |   8  |     |   9  |     |  10  |     |  11  |     |  12  |     |  XX  |\n"
				+ "|------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|     |------|";
		gcStub.printPlayerHand(0);
		assertEquals(expectedResult, outContent.toString().trim());
	}

	// Test case INTE0907: To test whether the system can add null history, that
	// means the player "SKIP"s his/her turn.
	@Test
	void testGameController_addHistory_skip() {
		class GameControllerStub extends GameController {
			public GameControllerStub() {
				super(null, null, 0, 1, 2, 0, false);
			}

			public void printHistory(History history) {
				System.out.println("[Round " + history.getRound() + "] [Turn " + history.getTurn() + "] ["
						+ history.getPlayerName() + "]: " + history.printCurrentPlay());
			}

			public void printHistories() {
				printHistory(histories.get(0));
			}

		}
		GameControllerStub gcStub = new GameControllerStub();
		gcStub.addHistory("Alice", null);
		gcStub.printHistories();
		String expectedResult = "[Round 1] [Turn 2] [Alice]: SKIP";
		assertEquals(expectedResult, outContent.toString().trim());
	}

	// Test case INTE0908: Check if the printing result task in
	// printRemainingNumberOfCards function is correct or not.
	@Test
	void testGameController_printRemainingNumberOfCards_withStub() {
		class GameControllerStub extends GameController {

			public GameControllerStub(AbstractPlayer[] players, int currentPlayerIndex) {
				super(null, players, 0, 0, 1, 0, false);
				players[0].setName("Alice");
				players[1].setName("Bob");
				players[2].setName("Candy");
				players[3].setName("David");
				this.currentPlayerIndex = currentPlayerIndex;
			}
		}

		Dealer dealer = new Dealer();
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();

		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				dealer.deal(players[i].getHand());
			}
		}

		int currentPlayerIndex = 0;
		GameControllerStub gcStub = new GameControllerStub(players, currentPlayerIndex);

		gcStub.printRemainingNumberOfCards();

		String expected_output1 = "Bob still have 13 card(s).\r\n" + "Candy still have 13 card(s).\r\n"
				+ "David still have 13 card(s).";
		assertEquals(expected_output1, outContent.toString().trim());
	}

	// Test case INTE0909: Check if the program can print the correct number of
	// remaining cards of other players when one player wins the game.
	@Test
	void testGameController_printRemainingCard_playerWin() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 3, 1, 1, 0, false);
			}
		}
		Dealer dealer = new Dealer();
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}
		players[0].setName("Alice");
		players[1].setName("Bob");
		players[2].setName("Candy");
		players[3].setName("David");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				dealer.deal(players[i].getHand());
			}

		}
		GameControllerStub gcStub = new GameControllerStub(players);
		gcStub.printRemainingNumberOfCards();
		String expectedResult = "Alice still have 13 card(s).\r\n" + "Bob still have 13 card(s).\r\n"
				+ "Candy still have 13 card(s).";
		assertEquals(expectedResult, outContent.toString().trim());
	}

	// Test case INTE0910: Check if the program can print the last history with no
	// history record when it is a player turn.
	@Test
	void testGameController_printLastHistory_historiesSizeIsZero() {

		GameController gc = GameController.getInstance();
		gc.printLastHistory();

		String expectedResult = "****************************************************\r\n" + "*No History\r\n"
				+ "****************************************************";
		assertEquals(expectedResult, outContent.toString().trim());
	}

	// Test case INTE0911: Check if the program can print the last history with size
	// more than one record and the last record is a SKIP when it is another player
	// turn.
	@Test
	void TestGameController_printLastHistory_historiesSizeMoreThanOneandEqualSKIP() {

		class GameControllerStub extends GameController {
			public GameControllerStub() {
				super(null, null, 0, 1, 1, 0, true);
			}
		}
		GameControllerStub gcStub = new GameControllerStub();
		Combination combo = new Combination();

		Card card1 = new Card(3, 1);
		combo.addCard(card1);
		Card card2 = new Card(3, 2);
		combo.addCard(card2);
		gcStub.addHistory("Alice", combo);
		gcStub.addHistory("Bob", null);

		gcStub.printLastHistory();
		String expectedResult = "****************************************************\r\n"
				+ "*History of last rounds' discarded card:\r\n" + "*[Round 1] [Turn 1] [Alice]: C6, H6\r\n"
				+ "****************************************************";
		assertEquals(expectedResult, outContent.toString().trim());
	}

	// Test case INTE0912: Check if the program can print the last history with size
	// more than one and the last record is a not SKIP when it is another player
	// turn.
	@Test
	void testGameController_printLastHistory_historiesSizeMoreThanOneandNotEqualSKIP() {

		class GameControllerStub extends GameController {
			public GameControllerStub() {
				super(null, null, 0, 1, 2, 0, true);
			}
		}
		GameControllerStub gcStub = new GameControllerStub();
		Combination combo = new Combination();
		Card card1 = new Card(2, 1);
		combo.addCard(card1);
		Card card2 = new Card(2, 2);
		combo.addCard(card2);
		gcStub.addHistory("Alice", combo);

		Combination combo2 = new Combination();
		Card card3 = new Card(3, 1);
		combo2.addCard(card3);
		Card card4 = new Card(3, 2);
		combo2.addCard(card4);
		Combination combination = new Combination(combo2);
		gcStub.addHistory("Bob", combination);

		gcStub.printLastHistory();
		String expectedResult = "****************************************************\r\n"
				+ "*History of last rounds' discarded card:\r\n" + "*[Round 1] [Turn 2] [Bob]: C6, H6\r\n"
				+ "****************************************************";
		assertEquals(expectedResult, outContent.toString().trim());
	}

	// Test case INTE0913: Check if the program can print the last history when
	// first player skips.
	@Test
	void testGameController_printLastHistory_historiesWhenFirstPlayerSkip() {

		class GameControllerStub extends GameController {
			public GameControllerStub() {
				super(null, null, 0, 1, 1, 0, true);
			}
		}
		GameControllerStub gcstub = new GameControllerStub();
		gcstub.addHistory("Alice", null);
		gcstub.printLastHistory();
		String expectedOutput = "****************************************************\r\n"
				+ "*History of last rounds' discarded card:\r\n"
				+ "****************************************************";
		assertEquals(expectedOutput, outContent.toString().trim());
	}

	// Test case INTE0914: Check if the program can print the correct information
	// when the user chooses print history with no history record found.
	@Test // unit test
	void testGameController_printHistories_historiesSizeIsZero() {

		class GameControllerStub extends GameController {
			public GameControllerStub() {
				super(null, null, 0, 1, 1, 0, true);
			}

			public void discard() {
				return;
			}
		}
		GameControllerStub gcstub = new GameControllerStub();
		gcstub.printHistories();
		String expectedResult = "****************************************************\r\n" + "No History\r\n"
				+ "****************************************************";
		assertEquals(expectedResult, outContent.toString().trim());
	}

	// Test case INTE0915: Check if the program can print the correct information
	// when the user chooses print history with only one history record found.
	@Test
	void testGameController_printHistories_historiesSizeIsOne() {

		class GameControllerStub extends GameController {
			public GameControllerStub() {
				super(null, null, 0, 1, 1, 0, true);
			}

			public void discard() {
				return;
			}
		}
		GameControllerStub gcstub = new GameControllerStub();
		Combination combo = new Combination();
		combo.addCard(new Card(3, 1));
		combo.addCard(new Card(3, 3));
		gcstub.addHistory("Alice", combo);
		gcstub.printHistories();

		String expectedOutput = "****************************************************\r\n"
				+ "history of discarded card: \r\n" + "*[Round 1] [Turn 1] [Alice]: C6, S6\r\n"
				+ "****************************************************";
		assertEquals(expectedOutput, outContent.toString().trim());
	}

	// Test case INTE0916: Check if the program can print the correct information
	// when the user choose print history with more than one history records found.
	@Test
	void testGameController_printHistories_historiesSizeMoreThanOne_() {

		class GameControllerStub extends GameController {
			public GameControllerStub() {
				super(null, null, 0, 1, 1, 0, true);
			}

			public void incrementTurn() {
				turn++;
			}

			public void discard() {
				return;
			}
		}
		GameControllerStub gcstub = new GameControllerStub();
		Combination combo = new Combination();
		Card card1 = new Card(2, 1);
		combo.addCard(card1);
		Card card2 = new Card(2, 2);
		combo.addCard(card2);
		gcstub.addHistory("Alice", combo);
		gcstub.incrementTurn();

		Combination combo2 = new Combination();
		Card card3 = new Card(3, 1);
		combo2.addCard(card3);
		Card card4 = new Card(3, 2);
		combo2.addCard(card4);
		gcstub.addHistory("Bob", combo2);
		gcstub.incrementTurn();

		Combination combo3 = new Combination();
		Card card5 = new Card(8, 1);
		combo3.addCard(card5);
		Card card6 = new Card(8, 2);
		combo3.addCard(card6);
		gcstub.addHistory("Candy", combo3);

		gcstub.printHistories();
		String expectedOutput = "****************************************************\r\n"
				+ "history of discarded card: \r\n" + "*[Round 1] [Turn 1] [Alice]: C5, H5\r\n"
				+ "*[Round 1] [Turn 2] [Bob]: C6, H6\r\n" + "*[Round 1] [Turn 3] [Candy]: CJ, HJ\r\n"
				+ "****************************************************";
		assertEquals(expectedOutput, outContent.toString().trim());
	}

	// Test case INTE0917: Check if the printing result task in printRoundInfo
	// function is correct or not.
	@Test
	void testGameController_printRoundInfo_withStub() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players, int round, int turn) {
				super(null, players, 0, 1, 1, 0, true);
				this.currentPlayer = players[this.currentPlayerIndex];
				this.currentPlayer.setName("Alice");
				this.round = round;
				this.turn = turn;
			}
		}

		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}
		int round = 1;
		int turn = 1;
		GameControllerStub gcStub = new GameControllerStub(players, round, turn);
		gcStub.printRoundInfo();

		String expected_result = "Now is turn 1 of round 1!\r\n" + "" + "It is your turn, Alice:";
		assertEquals(expected_result, outContent.toString().trim());

	}

	// Test case INTE0918: To check when the player skips his/her turn, whether the
	// system can pass one card from previous play of current player to the previous
	// play of next player .
	@Test
	void testGameController_handleSkipPlay_previousPlayCardNumberIsOne() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				this.currentPlayer = players[this.currentPlayerIndex];
				this.nextPlayer = players[this.nextPlayerIndex()];
				this.currentPlayer.setName("Alice");
				this.nextPlayer.setName("Bob");
				Play currentPlay = currentPlayer.getPlay();
				Card card = new Card(0, 0);
				currentPlay.getPreviousCombination().addCard(card);

			}

			public String toString() {
				return this.nextPlayer.toString();
			}
		}
		Dealer dealer = new Dealer();
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				dealer.deal(players[i].getHand());
			}

			players[i].sort();
		}
		GameControllerStub gcStub = new GameControllerStub(players);
		gcStub.handleSkipPlay();
		String expectedResult = "{ name=Bob }\n" + " ,play={\n" + "currentCombination={ \n" + "combination{\n"
				+ "cardNo=0, \n" + "}, \n" + "}\n" + "previousCombination={ \n" + "combination{\n" + "cardNo=1, \n"
				+ "Card{index= 0, value= 0, suit= 0, discarded= false, CardSymbol= D3}\n" + "}, \n" + "}\n" + " } \n";
		String actualResult = gcStub.toString();
		assertEquals(expectedResult, actualResult);
	}

	// Test case INTE0919: To check when the player skips his/her turn, whether the
	// system can pass two cards from previous play of current player to the
	// previous play of next player.
	@Test
	void testGameController_handleSkipPlay_previousPlayCardNumberIsTwo() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				this.currentPlayer = players[this.currentPlayerIndex];
				this.nextPlayer = players[this.nextPlayerIndex()];
				this.currentPlayer.setName("Alice");
				this.nextPlayer.setName("Bob");
				Play currentPlay = currentPlayer.getPlay();
				Card card1 = new Card(0, 0);
				Card card2 = new Card(1, 0);
				currentPlay.getPreviousCombination().addCard(card1);
				currentPlay.getPreviousCombination().addCard(card2);
			}

			public String toString() {
				return this.nextPlayer.toString();
			}
		}
		Dealer dealer = new Dealer();
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				dealer.deal(players[i].getHand());
			}

			players[i].sort();
		}
		GameControllerStub gcStub = new GameControllerStub(players);
		gcStub.handleSkipPlay();
		String expectedResult = "{ name=Bob }\n" + " ,play={\n" + "currentCombination={ \n" + "combination{\n"
				+ "cardNo=0, \n" + "}, \n" + "}\n" + "previousCombination={ \n" + "combination{\n" + "cardNo=2, \n"
				+ "Card{index= 0, value= 0, suit= 0, discarded= false, CardSymbol= D3}\n"
				+ "Card{index= 4, value= 1, suit= 0, discarded= false, CardSymbol= D4}\n" + "}, \n" + "}\n" + " } \n";
		String actualResult = gcStub.toString();
		assertEquals(expectedResult, actualResult);

	}

	// Test case INTE0920: To check when the player skips his/her turn, whether the
	// system can pass five cards from previous play of current player to the
	// previous play of next player.
	@Test
	void testGameController_handleSkipPlay_previousPlayCardNumberIsFive() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				this.currentPlayer = players[this.currentPlayerIndex];
				this.nextPlayer = players[this.nextPlayerIndex()];
				this.currentPlayer.setName("Alice");
				this.nextPlayer.setName("Bob");
				Play currentPlay = currentPlayer.getPlay();
				Card card1 = new Card(0, 0);
				Card card2 = new Card(1, 0);
				Card card3 = new Card(2, 0);
				Card card4 = new Card(3, 0);
				Card card5 = new Card(4, 0);
				currentPlay.getPreviousCombination().addCard(card1);
				currentPlay.getPreviousCombination().addCard(card2);
				currentPlay.getPreviousCombination().addCard(card3);
				currentPlay.getPreviousCombination().addCard(card4);
				currentPlay.getPreviousCombination().addCard(card5);

			}

			public String toString() {
				return this.nextPlayer.toString();
			}
		}
		Dealer dealer = new Dealer();
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				dealer.deal(players[i].getHand());
			}

			players[i].sort();
		}
		GameControllerStub gcStub = new GameControllerStub(players);
		gcStub.handleSkipPlay();
		String expectedResult = "{ name=Bob }\n" + " ,play={\n" + "currentCombination={ \n" + "combination{\n"
				+ "cardNo=0, \n" + "}, \n" + "}\n" + "previousCombination={ \n" + "combination{\n" + "cardNo=5, \n"
				+ "Card{index= 0, value= 0, suit= 0, discarded= false, CardSymbol= D3}\n"
				+ "Card{index= 4, value= 1, suit= 0, discarded= false, CardSymbol= D4}\n"
				+ "Card{index= 8, value= 2, suit= 0, discarded= false, CardSymbol= D5}\n"
				+ "Card{index= 12, value= 3, suit= 0, discarded= false, CardSymbol= D6}\n"
				+ "Card{index= 16, value= 4, suit= 0, discarded= false, CardSymbol= D7}\n" + "}, \n" + "}\n" + " } \n";
		String actualResult = gcStub.toString();
		assertEquals(expectedResult, actualResult);
	}

	// Test case INTE0921: To check if the function of prepareGame is correct or
	// not.
	@Test
	void testGameController_prepareGame_withStub() {

		GameController gc = GameController.getInstance();
		gc.prepareGame();

		String expected_result = "BBBBBBBBBBBBBBBBB     iiii                            222222222222222    \r\n"
				+ "B::::::::::::::::B   i::::i                          2:::::::::::::::22  \r\n"
				+ "B::::::BBBBBB:::::B   iiii                           2::::::222222:::::2 \r\n"
				+ "BB:::::B     B:::::B                                 2222222     2:::::2 \r\n"
				+ "  B::::B     B:::::Biiiiiii    ggggggggg   ggggg                 2:::::2 \r\n"
				+ "  B::::B     B:::::Bi:::::i   g:::::::::ggg::::g                 2:::::2 \r\n"
				+ "  B::::BBBBBB:::::B  i::::i  g:::::::::::::::::g              2222::::2  \r\n"
				+ "  B:::::::::::::BB   i::::i g::::::ggggg::::::gg         22222::::::22   \r\n"
				+ "  B::::BBBBBB:::::B  i::::i g:::::g     g:::::g        22::::::::222     \r\n"
				+ "  B::::B     B:::::B i::::i g:::::g     g:::::g       2:::::22222        \r\n"
				+ "  B::::B     B:::::B i::::i g:::::g     g:::::g      2:::::2             \r\n"
				+ "  B::::B     B:::::B i::::i g::::::g    g:::::g      2:::::2             \r\n"
				+ "BB:::::BBBBBB::::::Bi::::::ig:::::::ggggg:::::g      2:::::2       222222\r\n"
				+ "B:::::::::::::::::B i::::::i g::::::::::::::::g      2::::::2222222:::::2\r\n"
				+ "B::::::::::::::::B  i::::::i  gg::::::::::::::g      2::::::::::::::::::2\r\n"
				+ "BBBBBBBBBBBBBBBBB   iiiiiiii    gggggggg::::::g      22222222222222222222\r\n"
				+ "                                        g:::::g                          \r\n"
				+ "                            gggggg      g:::::g                          \r\n"
				+ "                            g:::::gg   gg:::::g                          \r\n"
				+ "                             g::::::ggg:::::::g                          \r\n"
				+ "                              gg:::::::::::::g                           \r\n"
				+ "                                ggg::::::ggg                             \r\n"
				+ "                                   gggggg                                \r\n"
				+ "  ____    U  ___ u   _  __  U _____ u   ____                   ____      _      __  __  U _____ u \r\n"
				+ "U|  _\"\\ u  \\/\"_ \\/  |\"|/ /  \\| ___\"|/U |  _\"\\ u             U /\"___|uU  /\"\\  uU|' \\/ '|u\\| ___\"|/ \r\n"
				+ "\\| |_) |/  | | | |  | ' /    |  _|\"   \\| |_) |/             \\| |  _ / \\/ _ \\/ \\| |\\/| |/ |  _|\"   \r\n"
				+ " |  __/.-,_| |_| |U/| . \\\\u  | |___    |  _ <                | |_| |  / ___ \\  | |  | |  | |___   \r\n"
				+ " |_|    \\_)-\\___/   |_|\\_\\   |_____|   |_| \\_\\                \\____| /_/   \\_\\ |_|  |_|  |_____|  \r\n"
				+ " ||>>_       \\\\   ,-,>> \\\\,-.<<   >>   //   \\\\_               _)(|_   \\\\    >><<,-,,-.   <<   >>  \r\n"
				+ "(__)__)     (__)   \\.)   (_/(__) (__) (__)  (__)             (__)__) (__)  (__)(./  \\.) (__) (__) \r\n\r\n\r\n"
				+ "******************************************************************************************************";

		assertEquals(expected_result, outContent.toString().trim());
	}

	// Test case UNIT0922: To test whether the player can skip after entered action
	// "1".
	@Test
	void testGameController_discard_inputEqualToZero() {
		class GameControllerStub extends GameController {
			public GameControllerStub() {
				super(null, null, 1, 1, 5, 0, false);
			}

			public int getCurrentPlayerIndex() {
				return this.currentPlayerIndex;
			}

			public String promptPlayerInput() {
				return "0";
			}

			public void skip() {
				currentPlayerIndex = nextPlayerIndex();
			}
		}

		GameControllerStub gc = new GameControllerStub();
		gc.discard();
		assertEquals(2, gc.getCurrentPlayerIndex());
	}

	// Test case INTE0923: To test whether the player can play any valid combination
	// of card as after three consecutive skip when this player does not win.
	@Test
	void testGameController_discard_skipCountEqualFourAndPlayerNotWin() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players, Dealer dealer) {
				super(dealer, players, 1, 1, 1, 4, false);
			}

			public String promptPlayerInput() {
				return "1";
			}

			public boolean handleDiscardCards(String playerInput) {
				return true;
			}

			public void setCurrentPlayer(AbstractPlayer player) {
				this.currentPlayer = player;
			}

			public AbstractPlayer getCurrentPlayer() {
				return this.currentPlayer;
			}

			public int getTurn() {
				return this.turn;
			}

		}
		Dealer dealer = new Dealer();
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				dealer.deal(players[i].getHand());
			}

			players[i].sort();
		}
		GameControllerStub gcStub = new GameControllerStub(players, dealer);
		gcStub.setCurrentPlayer(players[1]);
		Card card1 = new Card(0, 0);
		AbstractPlayer curr = gcStub.getCurrentPlayer();
		Play currentPlay = curr.getPlay();
		currentPlay.getPreviousCombination().addCard(card1);
		gcStub.discard();
		int expectedResult = 2;
		assertEquals(expectedResult, gcStub.getTurn());

	}

	// Test case INTE0924: To test whether the system can end when a player wins.
	@Test
	void testGameController_discard_skipCountIsNotEqualFourAndPlayerWin() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players, Dealer dealer) {
				super(dealer, players, 1, 1, 1, 3, false);
			}

			public String promptPlayerInput() {
				return "1";
			}

			public boolean handleDiscardCards(String playerInput) {
				return true;
			}

			public void printWinningMessages() {
				System.out.println("Win");
			}

			public void setCurrentPlayer(AbstractPlayer player) {
				this.currentPlayer = player;
			}

			public AbstractPlayer getCurrentPlayer() {
				return this.currentPlayer;
			}

			public boolean getEndGame() {
				return this.endGame;
			}
		}
		Dealer dealer = new Dealer();
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				dealer.deal(players[i].getHand());
			}
			players[i].sort();
		}
		GameControllerStub gcStub = new GameControllerStub(players, dealer);
		gcStub.setCurrentPlayer(players[1]);
		AbstractPlayer curr = gcStub.getCurrentPlayer();
		Hand currentPlay = curr.getHand();
		currentPlay.setCardNo(0);
		gcStub.discard();
		assertEquals(true, gcStub.getEndGame());
	}

	// Test case INTE0925: To test whether the system can print error message when
	// the play is invalid.
	@Test
	void testGameController_discard_playInvalid() {
		class GameControllerStub extends GameController {
			public String input = "1\n0";
			public InputStream testIn = new ByteArrayInputStream(input.getBytes());
			public Scanner testsc = new Scanner(testIn);

			public GameControllerStub(AbstractPlayer[] players, Dealer dealer) {
				super(dealer, players, 1, 2, 1, 4, false);
				setScanner(testsc);
			}

			public boolean handleDiscardCards(String playerInput) {
				return false;
			}

			public void setCurrentPlayer(AbstractPlayer player) {
				this.currentPlayer = player;
			}

			public void testdiscard() {
				try {
					System.setIn(testIn);
					discard();

				} finally {
					System.setIn(System.in);
				}
			}

			public void skip() {
				currentPlayerIndex = nextPlayerIndex();
			}
		}
		Dealer dealer = new Dealer();
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				dealer.deal(players[i].getHand());
			}
			players[i].sort();
		}

		GameControllerStub gcStub = new GameControllerStub(players, dealer);
		gcStub.setCurrentPlayer(players[1]);
		gcStub.testdiscard();
		String expectresult = "Input the card index(s) to indicate the card you want to play (Input 0 to discard this round):Your play is invalid! Please choose again!\r\n"
				+ "Input the card index(s) to indicate the card you want to play (Input 0 to discard this round):";
		assertEquals(expectresult, outContent.toString().trim());
	}

	// Test case INTE0926: To test the handleDiscardCards method can return correct
	// boolean or not when the input is enter.
	@Test
	void testGameController_handleDiscardCards_playerInputIsEnter() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				this.currentPlayer = players[this.currentPlayerIndex];
				this.nextPlayer = players[this.nextPlayerIndex()];
				this.currentPlayer.setName("Alice");
				this.nextPlayer.setName("Bob");
			}
		}
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}

		GameControllerStub gcStub = new GameControllerStub(players);
		String testInput = "";
		boolean actualResult = gcStub.handleDiscardCards(testInput);
		boolean expectedResult = false;
		assertEquals(expectedResult, actualResult);
	}

	// Test case INTE0927: To test the handleDiscardCards method can return correct
	// boolean or not when the input index contains 0 which should be invalid.
	@Test
	void testGameController_handleDiscardCards_isNumberWithInputIndexHasZero() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				this.currentPlayer = players[this.currentPlayerIndex];
				this.nextPlayer = players[this.nextPlayerIndex()];
				this.currentPlayer.setName("Alice");
				this.nextPlayer.setName("Bob");
			}
		}
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}

		GameControllerStub gcStub = new GameControllerStub(players);
		String testInput = "1 0 3 5 7";
		boolean actualResult = gcStub.handleDiscardCards(testInput);
		boolean expectedResult = false;
		assertEquals(expectedResult, actualResult);
	}

	// Test case INTE0928: To test the handleDiscardCards method can return correct
	// boolean or not when the input index contains an index larger than 12 which
	// should be invalid.
	@Test
	void testGameController_handleDiscardCards_isNumberWithInputIndexLargerThanTwelve() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				this.currentPlayer = players[this.currentPlayerIndex];
				this.nextPlayer = players[this.nextPlayerIndex()];
				this.currentPlayer.setName("Alice");
				this.nextPlayer.setName("Bob");
			}
		}
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}

		GameControllerStub gcStub = new GameControllerStub(players);
		String testInput = "1 23 3 5 7";
		boolean actualResult = gcStub.handleDiscardCards(testInput);
		boolean expectedResult = false;
		assertEquals(expectedResult, actualResult);
	}

	// Test case INTE0929: To test the handleDiscardCards method can return correct
	// boolean or not when the input index contains an non-integer index which
	// should be invalid.
	@Test
	void testGameController_handleDiscardCards_isNumberWithInputIndexIsAChar() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				this.currentPlayer = players[this.currentPlayerIndex];
				this.nextPlayer = players[this.nextPlayerIndex()];
				this.currentPlayer.setName("Alice");
				this.nextPlayer.setName("Bob");
			}
		}
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}

		GameControllerStub gcStub = new GameControllerStub(players);
		String testInput = "1 2 3 E 7";
		boolean actualResult = gcStub.handleDiscardCards(testInput);
		boolean expectedResult = false;
		assertEquals(expectedResult, actualResult);
	}

	// Test case INTE0930: To test the handleDiscardCards method can return correct
	// boolean or not when the input index refers to cards that have been discarded.
	// The input should be identified as invalid.
	@Test
	void testGameController_handleDiscardCards_isNumberWithInputIndexHasDiscardedCard() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				this.currentPlayer = players[this.currentPlayerIndex];
				this.nextPlayer = players[this.nextPlayerIndex()];
				this.currentPlayer.setName("Alice");
				this.nextPlayer.setName("Bob");
				Hand currentHand = currentPlayer.getHand();
				Card card1 = new Card(5, 0);
				card1.setDiscarded(true);
				currentHand.addCard(card1);
				currentHand.addCard(new Card(5, 1));
			}
		}
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}

		GameControllerStub gcStub = new GameControllerStub(players);
		String testInput = "1 2";
		boolean actualResult = gcStub.handleDiscardCards(testInput);
		boolean expectedResult = false;
		assertEquals(expectedResult, actualResult);
	}

	// Test case INTE0931: To test the handleDiscardCards method can return correct
	// boolean or not when the input index refers to cards that have NOT been
	// discarded. The input should be identified as valid.
	@Test
	void testGameController_handleDiscardCards_parseIntWithInputIndexAreValid() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				this.currentPlayer = players[this.currentPlayerIndex];
				this.nextPlayer = players[this.nextPlayerIndex()];
				this.currentPlayer.setName("Alice");
				this.nextPlayer.setName("Bob");
				Hand currentHand = currentPlayer.getHand();
				currentHand.addCard(new Card(3, 0));
				currentHand.addCard(new Card(4, 1));
				currentHand.addCard(new Card(5, 2));
				currentHand.addCard(new Card(6, 1));
				currentHand.addCard(new Card(7, 0));
			}
		}
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}

		GameControllerStub gcStub = new GameControllerStub(players);
		String testInput = "1 2 3 4 5";
		boolean actualResult = gcStub.handleDiscardCards(testInput);
		boolean expectedResult = true;
		assertEquals(expectedResult, actualResult);
	}

	// Test case INTE0932 To test the handleDiscardCards method can return correct
	// boolean when the input index refers to an invalid combination.
	@Test
	void testGameController_handleDiscardCards_isValidPlayWithInputIndexNotValid() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				this.currentPlayer = players[this.currentPlayerIndex];
				this.nextPlayer = players[this.nextPlayerIndex()];
				this.currentPlayer.setName("Alice");
				this.nextPlayer.setName("Bob");
				Hand currentHand = currentPlayer.getHand();
				currentHand.addCard(new Card(7, 0));
				currentHand.addCard(new Card(8, 1));
				currentHand.addCard(new Card(9, 2));
			}
		}
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}

		GameControllerStub gcStub = new GameControllerStub(players);
		String testInput = "1 2 3";
		boolean actualResult = gcStub.handleDiscardCards(testInput);
		boolean expectedResult = false;
		assertEquals(expectedResult, actualResult);
	}

	// Test case INTE0933: To test the handleDiscardCards method can return correct
	// boolean when the input index refers to an valid combination.
	@Test
	void testGameController_handleDiscardCards_isValidPlayWithInputIndexAreValid() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				this.currentPlayer = players[this.currentPlayerIndex];
				this.nextPlayer = players[this.nextPlayerIndex()];
				this.currentPlayer.setName("Alice");
				this.nextPlayer.setName("Bob");
				Hand currentHand = currentPlayer.getHand();
				currentHand.addCard(new Card(9, 0));
				currentHand.addCard(new Card(9, 1));
				currentHand.addCard(new Card(9, 2));
			}
		}
		AbstractPlayer[] players = new AbstractPlayer[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new HumanPlayer();
		}

		GameControllerStub gcStub = new GameControllerStub(players);
		String testInput = "1 2 3";
		boolean actualResult = gcStub.handleDiscardCards(testInput);
		boolean expectedResult = true;
		assertEquals(expectedResult, actualResult);
	}

}