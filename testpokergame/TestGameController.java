package testpokergame;

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


import pokergame.AbstractPlayer;
import pokergame.Card;
import pokergame.Dealer;
import pokergame.GameController;
import pokergame.Hand;
import pokergame.History;
import pokergame.HumanPlayer;
import pokergame.Play;

class TestGameController {
	public ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@BeforeEach
	void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
		
	}

	@AfterEach
	void tearDown() throws Exception {
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
	}
	

	@Test
	void testNextplayerIndexWhenCurrentIndexIsZero() {
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
	
	@Test
	void testNextplayerIndexWhenCurrentIndexIsOne() {
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
	
	@Test
	void testNextplayerIndexWhenCurrentIndexIsThree() {
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
	
	@Test
	void testDeal() {
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
	    String expectedResult = "Cards that you have: \tD3\tC4\tH5\tS6\tD7\tC8\tH9\tSX\tDJ\tCQ\tHK\tSA\tD2";
	    gcStub.printPlayerHand(0);
	    assertEquals(expectedResult, outContent.toString().trim());
	}
	
	@Test
	void testPrintOnePlayerHandWhenSomeCardsAreDiscarded() {
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
	    String expectedResult = "Cards that you have: \tD3\t--\tH5\tS6\tD7\tC8\tH9\tSX\tDJ\tCQ\tHK\tSA\t--";
	    gcStub.printPlayerHand(0);
	    assertEquals(expectedResult, outContent.toString().trim());
	}
	
	@Test
	void testPrintCardIndex() {
		GameController gc = GameController.getInstance();
		gc.printCardIndex();
		String expectedResult = "Card index: \t\t1\t2\t3\t4\t5\t6\t7\t8\t9\t10\t11\t12\t13";
		assertEquals(expectedResult, outContent.toString().trim());
	}
	
	@Test
	void testCheckmenuInputIsValidWhenInputIsZero() {
		GameController gc = GameController.getInstance();
		boolean actualResult = gc.checkMenuInput("0");
		assertEquals(true, actualResult);
	}
	
	@Test
	void testCheckmenuInputIsValidWhenInputIOne() {
		GameController gc = GameController.getInstance();
		boolean actualResult = gc.checkMenuInput("1");
		assertEquals(true, actualResult);
	}
	
	@Test
	void testCheckmenuInputIsValidWhenInputIsTwo() {
		GameController gc = GameController.getInstance();
		boolean actualResult = gc.checkMenuInput("2");
		assertEquals(true, actualResult);
	}
	
	@Test
	void testCheckmenuInputIsNotValid() {
		GameController gc = GameController.getInstance();
		boolean actualResult = gc.checkMenuInput("abc");
		assertEquals(false, actualResult);
	}
	
	@Test
	void testAddHistoryWhenSkip() {
		class GameControllerStub extends GameController {
			public GameControllerStub() {
				super(null, null, 0, 1, 2, 0, false);
			}
			public void printHistory(History history) {
			        System.out.println("[Round " + history.getRound() +"] [Turn "+ history.getTurn() + "] [" + history.getPlayerName() + "]: " + history.printCurrentPlay());
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
	
	
	@Test
	void testHandleSkipPlayWhenPreviousPlayCardNumberIsOne() {
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
	    String expectedResult = "{name=Bob},play={currentCombination={combination{cardNo=0,{}}}previousCombination={combination{cardNo=1,Card{index=0,value=0,suit=0,discarded=false,CardSymbol=D3}}}}";
	    String actualResult = gcStub.toString();
	    assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testHandleSkipPlayWhenPreviousPlayCardNumberIsTwo() {
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
	    String expectedResult = "{name=Bob},play={currentCombination={combination{cardNo=0,{}}}previousCombination={combination{cardNo=2,Card{index=0,value=0,suit=0,discarded=false,CardSymbol=D3}Card{index=4,value=1,suit=0,discarded=false,CardSymbol=D4}}}}";
	    String actualResult = gcStub.toString();
	    assertEquals(expectedResult, actualResult);
		
	}
	
	@Test
	void testHandleSkipPlayWhenPreviousPlayCardNumberIsFive() {
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
	    String expectedResult = "{name=Bob},play={currentCombination={combination{cardNo=0,{}}}previousCombination={combination{cardNo=5,Card{index=0,value=0,suit=0,discarded=false,CardSymbol=D3}Card{index=4,value=1,suit=0,discarded=false,CardSymbol=D4}Card{index=8,value=2,suit=0,discarded=false,CardSymbol=D5}Card{index=12,value=3,suit=0,discarded=false,CardSymbol=D6}Card{index=16,value=4,suit=0,discarded=false,CardSymbol=D7}}}}";
	    String actualResult = gcStub.toString();
	    assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testSkipWhenPlayerCannotSkipAsIsFirstPlayer() {
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
		String expectedResult = "Your cannot skip! Please choose again!\nPress Enter Key To Continue...";
		assertEquals(expectedResult, outContent.toString().trim());
	
		
		
	}
	
	@Test
	void testSkipWhenPlayerCannotSkipAsSkipBackToOriginalPlayer() {
		
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
		String expectedResult = "Your cannot skip! Please choose again!\nPress Enter Key To Continue...";
		assertEquals(expectedResult, outContent.toString().trim());
	
		
	}
	
	@Test
	void testPause() {
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
	
	
	@Test
	void incrementRoundWhenTurnIsOne() {
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
	
	
	@Test
	void incrementRoundWhenTurnIsFour() {
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
	
	@Test
	void incrementRoundWhenTurnIsGreaterThanFour() {
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

}
